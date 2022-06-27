package com.ruizurraca.reservationappdemo.login.data.repository

import com.ruizurraca.reservationappdemo.common.Prefs
import com.ruizurraca.reservationappdemo.common.saveCredentials
import com.ruizurraca.reservationappdemo.login.data.api.AimharderLoginApi
import com.ruizurraca.reservationappdemo.login.data.models.LoginModelApi
import com.ruizurraca.reservationappdemo.login.domain.repository.LoginRepository
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginResult
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val aimharderLoginApi: AimharderLoginApi) :
    LoginRepository {

    companion object {
        const val TAG = "LoginRepositoryImpl"
    }

    override suspend fun login(loginModelApi: LoginModelApi): LoginResult {
        val json = getLoginModelApiFake(true)
        val response = aimharderLoginApi.login(
            getLoginHeaders(),
            json.login,
            json.loginiframe,
            json.mail,
            json.pw
        )
        response.body()?.let { body ->
            val loginResult = LoginResult(loginModel = LoginModel.fromDTO(loginModelApi))
            manageDoc(Jsoup.parse(body))?.let {
                loginResult.errorString = it
            }
            return loginResult
        }
        return LoginResult("Error", loginModel = LoginModel.fromDTO(loginModelApi))
        //return LoginResult(loginModel=LoginModel.fromDTO(loginModelApi))
    }

    private fun manageDoc(doc: Document): String? {
        var error: String? = null
        doc.forEach {
            if (it.id() == "loginErrors") {
                error = it.text()
            }
        }
        return error
    }

    private fun getLoginHeaders(): Map<String, String> {
        val header = HashMap<String, String>()
        header["sec-ch-ua"] =
            "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\""
        header["sec-ch-ua-mobile"] = "?0"
        header["sec-ch-ua-platform"] = "\"macOS\""
        header["Upgrade-Insecure-Requests"] = "1"
        header["Content-Type"] = "application/x-www-form-urlencoded"
        header["User-Agent"] =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36"
        header["Accept"] =
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
        header["host"] = "aimharder.com"

        return header
    }

    private fun getLoginModelApiFake(correct: Boolean): LoginModelApi {
        val credentials = if (correct) {
            LoginModelApi(mail = "davidru85@gmail.com", pw = "8SoHiKHRpNJUrezEPC63")
        } else {
            LoginModelApi(mail = "mail@mail.com", pw = "wtf")
        }
        Prefs.saveCredentials(LoginModel.fromDTO(credentials))
        return credentials
    }

}
