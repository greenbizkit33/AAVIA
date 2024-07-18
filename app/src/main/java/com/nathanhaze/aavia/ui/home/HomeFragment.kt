package com.nathanhaze.aavia.ui.home

//import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton
import com.nathanhaze.aavia.R
import com.nathanhaze.aavia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root

        homeViewModel.happyList.observe(viewLifecycleOwner) { value ->
            createHappyButtons(value)
        }

        homeViewModel.lastHappyWord.observe(viewLifecycleOwner) { value ->
            if (value.isNullOrBlank()) {
                return@observe
            }
        }

        binding.btnSave.setOnClickListener { homeViewModel.saveList() }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createHappyButtons(array: ArrayList<String>) {
        clearButtons(binding.constraintLayout)
        val lastSelectedWord = homeViewModel.lastHappyWord.value
        val lastSelectedIndex = array.lastIndexOf(lastSelectedWord)
        array.forEachIndexed { index, element ->
            val button = getButton(element)
            // add the click listener
            if (index == array.size - 1) {
                val customDialogView: View = layoutInflater.inflate(R.layout.dialog_add, null)
                val editText = customDialogView.findViewById<EditText>(R.id.edit_text)
                val titleTextView = customDialogView.findViewById<TextView>(R.id.tv_dialog_title)
                val titleText = getString(R.string.dialog_title, element)
                titleTextView.text = titleText

                val alertDialog = AlertDialog.Builder(requireContext())
                    .setView(customDialogView)
                    .setPositiveButton("ADD") { _, _ ->
                        val happyWord = editText.text.toString()
                        editText.text.clear()
                        homeViewModel.updateHappyList(happyWord)
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        editText.text.clear()
                    }
                    .create()

                button.setOnClickListener {
                    alertDialog.show()
                }
            }

            if (index == lastSelectedIndex) {
                button.setBackgroundColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.button_selected
                    )
                )
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
        button.isAllCaps = false
        val formatTitle = title.replaceFirst(title[0], title[0].uppercaseChar())
        button.text = formatTitle
        button.id = generateViewId()
        return button
    }

    private fun clearButtons(v: ViewGroup) {
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