package com.udacity.shoestore.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.models.ShoeListViewModel

class ShoesFragment : Fragment() {
    private lateinit var binding: FragmentShoesBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)

        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(5,0,5,25)

            for (item in it.listIterator()) {
                val nameText = TextView(this.context)
                val sizeText = TextView(this.context)
                val companyText = TextView(this.context)
                val descriptionText = TextView(this.context)
                val parentLayout = LinearLayout(this.context)

                nameText.text = item.name
                sizeText.text = item.size.toString()
                companyText.text = item.company
                descriptionText.text = item.description

                nameText.setTextColor(Color.BLACK)
                sizeText.setTextColor(Color.BLACK)
                companyText.setTextColor(Color.BLACK)
                descriptionText.setTextColor(Color.BLACK)

                nameText.textSize = 26F;
                sizeText.textSize =22F
                companyText.textSize =20F
                descriptionText.textSize =15F

                parentLayout.orientation = LinearLayout.VERTICAL;
                parentLayout.setBackgroundColor(Color.LTGRAY)
                parentLayout.setPadding(10,10,10,10)
                parentLayout.addView(nameText)
                parentLayout.addView(sizeText)
                parentLayout.addView(companyText)
                parentLayout.addView(descriptionText)

                binding.shoeListView.addView(parentLayout, params)
            }
        })

        binding.addShoeButton.setOnClickListener {
            it.findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToShoeDetailFragment())
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_shoes)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}