package com.nathanhaze.aavia.ui.home

//import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton
import com.nathanhaze.aavia.R
import com.nathanhaze.aavia.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val dynamicButton = Button(requireActivity())
//        // setting layout_width and layout_height using layout parameters
//        dynamicButton.layoutParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        dynamicButton.text = "Dynamic Button"

        val root: View = binding.root

//        var array = homeViewModel.happyList.value
//        if (array.isNullOrEmpty()) {
//            array = arrayListOf<String>("Family", "Friends", "Work", "Other")
//        }
//
//        HappyList.saveArrayList(array)
        //    homeViewModel.happyList.value?.let { createHappyButtons(it) }

        homeViewModel.happyList.observe(viewLifecycleOwner) { value ->
            createHappyButtons(value)
        }

        homeViewModel.lastHappyWord.observe(viewLifecycleOwner) { value ->
            if (value.isNullOrBlank()) {
                return@observe
            }
            Log.d("nathanx", "last word " + value)
            //  addHappyButton(value)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addHappyButton(label: String) {
        val button = getButton(label)
        homeViewModel.happyList.value?.let {
            val index = it.size
            binding.constraintLayout.addView(button, index - 3)
            binding.flow.addView(button)
//            binding.flow.remove
        }

    }

    private fun createHappyButtons(array: ArrayList<String>) {
        clearImageView(binding.constraintLayout)
        array.forEachIndexed { index, element ->
            val button = getButton(element)
            if (index == array.size - 1) {
                val customDialogView: View = layoutInflater.inflate(R.layout.dialog_add, null)
                val editText = customDialogView.findViewById<EditText>(R.id.edit_text)
                val titleTextView = customDialogView.findViewById<TextView>(R.id.tv_dialog_title)
                val titleText = getString(R.string.dialog_title, element)
                titleTextView.text = titleText

                val alertDialog = AlertDialog.Builder(requireContext())
                    .setView(customDialogView)
                    .setPositiveButton("OK") { _, _ ->
                        val happyWord = editText.text.toString()
                        editText.text.clear();
                        homeViewModel.updateHappyList(happyWord)
                    }
                    .setNegativeButton("Cancel", null)
                    .create()

                button.setOnClickListener {
                    alertDialog.show()
                }
            }
            binding.constraintLayout.addView(button, index)
            binding.flow.addView(button)
        }
    }

    private fun getButton(title: String): Button {
        val button = MaterialButton(
            requireActivity(),
            null,
            com.google.android.material.R.attr.materialButtonOutlinedStyle
        )
        button.text = title
        button.id = generateViewId()
        return button
    }

    private fun clearImageView(v: ViewGroup) {
        var doBreak = false
        while (!doBreak) {
            val childCount = v.childCount
            var i = 0
            while (i < childCount) {
                val currentChild = v.getChildAt(i)
                if (currentChild is Button) {
                    v.removeView(currentChild)
                    break
                }
                i++
            }
            if (i == childCount) {
                doBreak = true
            }
        }
    }
}