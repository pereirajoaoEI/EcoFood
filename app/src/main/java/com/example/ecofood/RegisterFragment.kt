package com.example.ecofood

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */

class RegisterFragment : Fragment() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassowrd: EditText

    //Autenticação com o Firebase
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        cnfPassowrd = view.findViewById(R.id.reg_cnf_password)
        fAuth = Firebase.auth


        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
            var navRegister = activity as FragmentNavigation
            //Direcionar para o fragment do login quando se clica no botão
            navRegister.navigateFrag(LoginFragment(), false)
        }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }

        return view
    }

    //escreve no firebase o registo
    private fun firebaseSignUp() {

        fAuth.createUserWithEmailAndPassword(
            username.text.toString(), password.text.toString()
        ).addOnCompleteListener {
                task ->
            if (task.isSuccessful) {
                var navHome =  activity as FragmentNavigation
                navHome.navigateFrag(HomeFragment(),addToStack = true)
            } else {
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Validações do formulário (caso os inputs sejam nulos, etc)
    private fun validateEmptyForm() {
        when {
            //Verificar quando está o input vazio
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Insira um username!")
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Insira uma password!")
            }
            TextUtils.isEmpty(cnfPassowrd.text.toString().trim()) -> {
                cnfPassowrd.setError("Insira uma confirmação de password!")
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    cnfPassowrd.text.toString().isNotEmpty() -> {

                if (password.text.toString().length >= 5) {
                    if (password.text.toString() == cnfPassowrd.text.toString()) {
                        firebaseSignUp()
                        Toast.makeText(context, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        cnfPassowrd.setError("Passwords incompatíveis!")
                    }
                } else {
                    password.setError("Insira mais do que 5 caracteres!")
                }

            }
        }

    }


}