package com.ruizurraca.reservationappdemo.box.data.repository

import com.ruizurraca.reservationappdemo.box.data.api.AimharderBoxesApi
import com.ruizurraca.reservationappdemo.box.domain.repository.BoxesRepository
import com.ruizurraca.reservationappdemo.box.presentation.models.BoxResponseModel
import com.ruizurraca.reservationappdemo.box.presentation.models.BoxesListResponseModel
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class BoxesRepositoryImpl @Inject constructor(private val aimharderBoxesApi: AimharderBoxesApi) :
    BoxesRepository {

    companion object {
        const val TAG = "BoxesRepositoryImpl"
    }

    private val boxesList = mutableListOf<BoxResponseModel>()

    override suspend fun getBoxes(): BoxesListResponseModel {
        val response = aimharderBoxesApi.boxes()
        response.body()?.let { body ->
            manageDoc(Jsoup.parse(body))
        }
        return BoxesListResponseModel(boxesList)
    }

    private fun manageDoc(doc: Document) {
        doc.forEach {
            var url: String? = null
            var title: String? = null
            var photo: String? = null
            when (it.className()) {
                "ahMenuBoxName" -> {
                    url = it.allElements.attr("href")
                    title = it.text()
                    addToList(url, title, photo)
                }
                "ahMenuBoxPic" -> {
                    url = it.allElements.attr("onclick").split("\"").get(1)
                    photo = it.allElements.attr("src")
                    addToList(url, title, photo)
                }
            }
        }
    }

    private fun addToList(url: String? = null, title: String? = null, photo: String? = null) {
        url?.let { currentUrl ->
            val updatedBox: BoxResponseModel? = boxesList.find { it.url == currentUrl }?.apply {
                if (this.title == null)
                    this.title = title
                if (this.photo == null)
                    this.photo = photo
            }
            if (updatedBox == null) {
                boxesList.add(BoxResponseModel(url, title, photo))
            }
            updatedBox?.let { filledBox ->
                boxesList.replaceAll {
                    if (it.isFilled())
                        it
                    else
                        filledBox
                }
            }
        }
    }
}
