package com.formationandroid.fragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.formationandroid.fragments.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment()
{

    companion object
    {
        // Constantes :
        const val EXTRA_MEMO = "EXTRA_MEMO"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // fait le lien entre le xml du fragment et la classe kotlin du fragment :
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        // init :
        super.onActivityCreated(savedInstanceState)

        // récupération de l'argument depuis DetailActivity :
        val arguments = requireArguments()
        val intitule = arguments.getString(EXTRA_MEMO)

        // affichage de l'intitulé s'il a bien été passé en argument :
        intitule?.let { detail_memo.text = intitule }
    }

}