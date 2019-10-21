package com.mindinventory.mediaplayerdemo.presentation.activities

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mindinventory.mediaplayerdemo.R
import com.mindinventory.mediaplayerdemo.Utils.HttpHandler
import com.mindinventory.mediaplayerdemo.Utils.KeyUtils
import com.mindinventory.mediaplayerdemo.presentation.adapter.UserListAdapter
import com.mindinventory.mediaplayerdemo.presentation.base.BaseActivity
import com.mindinventory.mediaplayerdemo.presentation.model.*
import kotlinx.android.synthetic.main.activity_web_apicall.*
import org.json.JSONException
import org.json.JSONObject

class WebAPIcallActivity : BaseActivity() {
    private val TAG = this::class.java.simpleName
    override fun getContentResource() = R.layout.activity_web_apicall
    private val userListAdapter by lazy { UserListAdapter() }
    var usersList = arrayListOf<Example>()

    override fun initViews() {
        super.initViews()
        GetAPITask().execute()
        ivprofilePhoto.adapter = userListAdapter
    }

    inner class GetAPITask : AsyncTask<Void, Void, Example>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressbar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Void?): Example? {
            val sh = HttpHandler()
            val jsonStr = sh.makeServiceCall(getString(R.string.user_api_url))
            Log.i("WebAPIcallActivity", "Response from url: " + jsonStr)
            if (jsonStr != null) {
                try {
                    val jsonObj = JSONObject(jsonStr)
                    val results = jsonObj.getJSONArray("results")
                    for (i in 0 until results.length()) {

                        val mainjsonobj = results.getJSONObject(i)
                        val gender = mainjsonobj.getString(KeyUtils.GENDER)
                        val email = mainjsonobj.getString(KeyUtils.EMAIL)
                        val phone = mainjsonobj.getString(KeyUtils.PHONE)

                        val jsonobjlocation = mainjsonobj.getJSONObject(KeyUtils.LOCATION)

                        val street = jsonobjlocation.getJSONObject(KeyUtils.STREET)
                        val number = street.getInt(KeyUtils.NUMBER)
                        val name = street.getString(KeyUtils.NAME)

                        val jsonobjname = mainjsonobj.getJSONObject(KeyUtils.NAME)

                        val title = jsonobjname.getString(KeyUtils.TITLE)
                        val first = jsonobjname.getString(KeyUtils.FIRST)
                        val last = jsonobjname.getString(KeyUtils.LAST)

                        val jsonobjdob = mainjsonobj.getJSONObject(KeyUtils.DOB)
                        val age = jsonobjdob.getInt(KeyUtils.AGE)

                        val jsonobjpicture = mainjsonobj.getJSONObject(KeyUtils.PICTURE)
                        val large = jsonobjpicture.getString(KeyUtils.LARGE)

                        val example = Example()
                        example.gender = gender
                        example.phone = phone
                        example.email = email
                        example.name = Name(title, first, last)
                        example.street = Street(number, name)
                        example.dob = Dob(age.toString())
                        example.picture = Picture(large)
                        usersList.add(example)
                    }
                } catch (e: JSONException) {
                    Log.e("TAG", "Json parsing error: " + e.message)
                }
            } else {
                Log.e("TAG", "Couldn't get json from server.")
            }
            return null
        }

        override fun onPostExecute(result: Example?) {
            super.onPostExecute(result)
            progressbar.visibility = View.GONE
            userListAdapter.loadAllUsers(usersList)
        }
    }
}

