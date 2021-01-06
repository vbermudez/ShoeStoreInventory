package com.udacity.shoestore.navigation

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()
    private val shoeModel: Shoe = Shoe()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.shoeModel = shoeModel

        binding.saveButton.setOnClickListener{
            viewModel.addShoe(shoeModel)
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }

        binding.cancelButton.setOnClickListener{
            binding.nameText.text.clear()
            binding.sizeText.text.clear()
            binding.companyText.text.clear()
            binding.descriptionText.text.clear()
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }

        binding.sizeText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) binding.sizeText.text.clear()
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_shoe_detail)

        return binding.root
    }
}