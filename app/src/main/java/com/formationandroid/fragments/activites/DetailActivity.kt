package com.formationandroid.fragments.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.formationandroid.fragments.R
import com.formationandroid.fragments.fragments.DetailFragment

class DetailActivity : AppCompatActivity()
{

    companion object
    {
        // Constantes :
        const val EXTRA_MEMO = "EXTRA_MEMO"
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        // init :
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // récupération de l'argument depuis l'adapter :
        val intitule = intent.getStringExtra(EXTRA_MEMO)

        // fragment :
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(DetailFragment.EXTRA_MEMO, intitule)
        fragment.arguments = bundle

        // transaction :
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.conteneur_fragment, fragment)
        transaction.commit()
    }

}