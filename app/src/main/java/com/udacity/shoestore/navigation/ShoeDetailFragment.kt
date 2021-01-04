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
import com.udacity.shoestore.models.ShoeListViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        setHasOptionsMenu(false)

        binding.saveButton.setOnClickListener{
            var name = binding.nameText.text.toString()
            var size = binding.sizeText.text.toString()
            var company = binding.companyText.text.toString()
            var description = binding.descriptionText.text.toString()

            viewModel.addShoe(name, size.toDouble(), company, description)
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }
        binding.cancelButton.setOnClickListener{
            binding.nameText.text.clear()
            binding.sizeText.text.clear()
            binding.companyText.text.clear()
            binding.descriptionText.text.clear()
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_shoe_detail)

        return binding.root
    }
}