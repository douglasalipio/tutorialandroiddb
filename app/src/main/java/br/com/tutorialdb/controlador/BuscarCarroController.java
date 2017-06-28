package br.com.tutorialdb.controlador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import br.com.tutorialdb.R;
import br.com.tutorialdb.exception.IdInvalidoException;
import br.com.tutorialdb.negocio.entidade.Carro;
import br.com.tutorialdb.negocio.model.CarroModel;

public class BuscarCarroController extends Activity implements OnClickListener {

	private Button buttonBuscarDados;
	private EditText editId;
	private CarroModel carroModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.buscar_dados_view);
		this.buttonBuscarDados = (Button) findViewById(R.idBuscaDado.buttonBuscarDados);
		this.buttonBuscarDados.setOnClickListener(this);
		this.editId = (EditText) findViewById(R.idBuscaDado.editId);
	}

	@Override
	public void onClick(View v) {

		try {

			carroModel = new CarroModel(getApplicationContext());
			Carro carro = carroModel.tratarBuscaCarro(editId.getText()
					.toString());

			Intent it = new Intent(this, AtualizarCarroController.class);
			it.putExtra("carro", carro);

			startActivity(it);

		} catch (NumberFormatException e) {

			Log.e("Error", "Formato incorreto");

		} catch (IdInvalidoException e) {

			Log.e("Error", "ID inv�lido");
		}

	}
}
