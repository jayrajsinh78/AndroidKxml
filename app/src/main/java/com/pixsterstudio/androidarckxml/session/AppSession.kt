package com.pixsterstudio.androidarckxml.session

import android.content.Context
import com.google.gson.Gson


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSession @Inject
constructor(private val appPreferences: AppPreferences,
            private val context: Context) : Session {

    private val gson: Gson = Gson()

    override var user: String? = null
        get() {
         return field
         /*   if (field == null) {
                val userJSON = appPreferences.getString(USER_JSON)
                field = gson.fromJson(userJSON, User::class.java)
            }
            return field*/
        }
        set(value) {
            field = value
            val userJson = gson.toJson(value)
            if (userJson != null)
                appPreferences.putString(USER_JSON, userJson)
        }



    override var userSession: String
        get() = appPreferences.getString(Session.USER_SESSION)
        set(userSession) = appPreferences.putString(Session.USER_SESSION, userSession)


    override var userId: String
        get() = appPreferences.getString(Session.USER_ID)
        set(userId) = appPreferences.putString(Session.USER_ID, userId)


  /*  override*//* open below comment after Firebase integration *//*//token = FirebaseInstanceId.getInstance().getToken();
    val deviceId: String
        get() {
            var token = ""
            if (token.isEmpty())
                token = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

            return token
        }*/

    override//  return StringUtils.equalsIgnoreCase(appPreferences.getString(Common.LANGUAGE), "ar") ? LANGUAGE_ARABIC : LANGUAGE_ENGLISH;
    val language: String
        get() = "en"


    override fun clearSession() {
        appPreferences.clearAll()
    }


    companion object {
        const val USER_JSON = "user_json"
        const val COLOR_JSON = "color_json"
        const val DRAFT_JSON = "draft_json"
    }


}
