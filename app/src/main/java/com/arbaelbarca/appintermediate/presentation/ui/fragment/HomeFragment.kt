package com.arbaelbarca.appintermediate.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arbaelbarca.appintermediate.R
import com.arbaelbarca.appintermediate.data.datasource.local.response.ResponseListUsers
import com.arbaelbarca.appintermediate.data.network.apiservice.ApiClient
import com.arbaelbarca.appintermediate.databinding.FragmentHomeBinding
import com.arbaelbarca.appintermediate.presentation.ui.adapter.AdapterUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var fragmentBinding: FragmentHomeBinding

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
        fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
    }

    private fun initial() {
        initRetrofit()
        initOnClick()
    }

    private fun initOnClick() {
        fragmentBinding.btnAdd.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout, HomeAddFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initRetrofit() {
        val call = ApiClient.getApiService().getAllUsers()
        call.enqueue(object : Callback<ResponseListUsers> {
            override fun onResponse(
                call: Call<ResponseListUsers>,
                response: Response<ResponseListUsers>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    initAdapter(data?.data as List<ResponseListUsers.Data>?)
                }
            }

            override fun onFailure(call: Call<ResponseListUsers>, t: Throwable) {

            }

        })
    }

    fun initAdapter(data: List<ResponseListUsers.Data>?) {
        val adapterUsers = AdapterUsers(data as MutableList<ResponseListUsers.Data>)
        fragmentBinding.rvListUsers.apply {
            adapter = adapterUsers
            layoutManager = LinearLayoutManager(requireContext())
            hasFixedSize()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}