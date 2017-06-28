package br.com.tutorialdb.controlador;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.tutorialdb.R;
import br.com.tutorialdb.exception.GenericoException;
import br.com.tutorialdb.negocio.model.CarroModel;

public class ExcluirCarroController extends Activity implements OnClickListener {

	private CarroModel carroModel;
	private EditText editId;
	private Button buttonBuscar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buscar_dados_view);

		this.buttonBuscar = (Button) findViewById(R.idBuscaDado.buttonBuscarDados);
		this.buttonBuscar.setOnClickListener(this);
		this.editId = (EditText) findViewById(R.idBuscaDado.editId);

	}

	/**
	 * M�todo utilizado para excluir um carro.
	 * 
	 * @param nomeCarro
	 */
	private void excluirCarro(String nomeCarro) {

		carroModel = new CarroModel(getApplicationContext());
		try {

			carroModel.tratarExclusaoCarro(nomeCarro);

		} catch (GenericoException e) {

			Log.e("Categoria", "Erro ao excluir Carro");
		}
	}

	@Override
	public void onClick(View v) {

		excluirCarro(editId.getText().toString());
		Toast.makeText(this, "Exclu�do com Sucesso !", Toast.LENGTH_SHORT)
				.show();
		finish();
	}
}
