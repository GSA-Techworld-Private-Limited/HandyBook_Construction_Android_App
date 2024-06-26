package com.example.handybook_construction_android_app.category_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.handybook_construction_android_app.R
import com.example.handybook_construction_android_app.home_fragment.NewListingAdapter
import com.example.handybook_construction_android_app.home_fragment.TopBrandAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Category_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_, container, false).apply {


            val recyclerView: RecyclerView = findViewById(R.id.recyclerViewFurniture)
            recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = FurnitureAdapter()


            // Attach LinearSnapHelper
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)


            val recyclerView1: RecyclerView = findViewById(R.id.recyclerViewElectronics)
            recyclerView1.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView1.adapter = ElectronicAdapter()


            // Attach LinearSnapHelper
            val snapHelper1 = LinearSnapHelper()
            snapHelper1.attachToRecyclerView(recyclerView1)



            val recyclerView2: RecyclerView = findViewById(R.id.recyclerViewFlooring)
            recyclerView2.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView2.adapter = FlooringAdapter()


            // Attach LinearSnapHelper
            val snapHelper2 = LinearSnapHelper()
            snapHelper2.attachToRecyclerView(recyclerView2)

            val recyclerView3: RecyclerView = findViewById(R.id.recyclerViewPlumbingSupplies)
            recyclerView3.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView3.adapter = plumbing_adapter()


            // Attach LinearSnapHelper
            val snapHelper3 = LinearSnapHelper()
            snapHelper3.attachToRecyclerView(recyclerView3)


        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Category_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Category_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}