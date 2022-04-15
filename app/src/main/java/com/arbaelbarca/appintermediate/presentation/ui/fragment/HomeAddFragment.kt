package com.arbaelbarca.appintermediate.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arbaelbarca.appintermediate.data.network.apiservice.ApiClient
import com.arbaelbarca.appintermediate.databinding.FragmentHomeAddBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var fragmentBinding: FragmentHomeAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentHomeAddBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
    }

    private fun initial() {
        initOnClick()
    }

    private fun initOnClick() {
        fragmentBinding.btnAddUsers.setOnClickListener {
            val getName = fragmentBinding.edNameUsersAdd.text.toString()
            val getJobs = fragmentBinding.edJobUsersAdd.text.toString()
            addUsersList(getName, getJobs)
        }
    }

    private fun addUsersList(name: String, jobs: String) {
        val mutableMap = mutableMapOf(
            "name" to name,
            "job" to jobs
        )
        val callAddUsers = ApiClient.getApiService().addUsers(
            mutableMap
        )

        callAddUsers.enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Toast.makeText(requireContext(), "Succes data baru", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}