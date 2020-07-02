package com.formationandroid.fragments.activites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.formationandroid.fragments.MemosAdapter
import com.formationandroid.fragments.R
import com.formationandroid.fragments.bdd.AppDatabaseHelper
import com.formationandroid.fragments.bdd.MemoDTO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

	companion object
	{
		// Constantes :
		const val CLE_DERNIERE_POSITION = "CLE_DERNIERE_POSITION"
	}
	
	// Adapter :
	private lateinit var memosAdapter: MemosAdapter

	
	override fun onCreate(savedInstanceState: Bundle?)
	{
		// init :
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// à ajouter pour de meilleures performances :
		liste_memos.setHasFixedSize(true)

		// layout manager, décrivant comment les items sont disposés :
		val layoutManager = LinearLayoutManager(this)
		liste_memos.layoutManager = layoutManager

		// contenu d'exemple :
		val listeMemoDTO: MutableList<MemoDTO> = AppDatabaseHelper.getDatabase(this).memosDAO().getListeMemos()

		// adapter :
		memosAdapter = MemosAdapter(listeMemoDTO, this)
		liste_memos.adapter = memosAdapter

		// affichage de la dernière position cliquée, si possible :
		val preferences = PreferenceManager.getDefaultSharedPreferences(this)
		val dernierePosition = preferences.getInt(CLE_DERNIERE_POSITION, -1)
		if (dernierePosition > -1)
		{
			// affichage du toast :
			Toast.makeText(this, getString(R.string.main_message_position, dernierePosition), Toast.LENGTH_LONG).show()
		}
	}

	/**
	 * Listener clic bouton valider.
	 * @param view Bouton valider
	 */
	@Suppress("UNUSED_PARAMETER")
	fun onClickBoutonValider(view: View?)
	{
		// ajout du mémo en base :
		AppDatabaseHelper.getDatabase(this).memosDAO().insert(MemoDTO(0, saisie_memo.text.toString()))

		// rechargement de la liste de mémos pour mettre à jour l'affichage :
		memosAdapter.updateMemos(AppDatabaseHelper.getDatabase(this).memosDAO().getListeMemos())

		// animation de repositionnement de la liste (sinon on ne voit pas l'item ajouté) :
		liste_memos.smoothScrollToPosition(0)

		// on efface le contenu de la zone de saisie :
		saisie_memo.setText("")
	}
	
}