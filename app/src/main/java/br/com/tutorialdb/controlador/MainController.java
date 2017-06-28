package br.com.tutorialdb.controlador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.tutorialdb.R;
import br.com.tutorialdb.exception.CampoObrigatorioCarroException;
import br.com.tutorialdb.negocio.entidade.Carro;
import br.com.tutorialdb.negocio.model.CarroModel;
import br.com.tutorialdb.persistencia.repositorio.DatabaseCreate;


/**
 * 
 * @author douglas
 * 
 */
public class MainController extends Activity implements OnClickListener {

	private Button enviar;
	private Button buscar;
	private Button excluir;

	private EditText nome;
	private EditText placa;
	private EditText modelo;

	private CarroModel carroNegocio;
	private DatabaseCreate dbCreate;
	private Carro carro;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		carroNegocio = new CarroModel(this.getApplication());
		dbCreate = new DatabaseCreate(this);

		nome = (EditText) findViewById(R.id.editNome);
		placa = (EditText) findViewById(R.id.editPlaca);
		modelo = (EditText) findViewById(R.id.editModelo);

		enviar = (Button) findViewById(R.id.buttonEnviar);
		buscar = (Button) findViewById(R.id.buttonBuscar);
		excluir = (Button) findViewById(R.id.buttonExcluir);

		enviar.setOnClickListener(this);
		buscar.setOnClickListener(this);
		excluir.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.buttonBuscar:

			abrirTelaBusca();
			break;

		case R.id.buttonEnviar:

			carro = recuperarInformacaoCarro();
			enviarCarro();
			limparCampos();

			break;

		case R.id.buttonExcluir:

			abrirTelaExclusao();
			break;
		}

	}

	/**
	 * Envia o objeto carro para ser validado.
	 * 
	 */
	private void enviarCarro() {

		try {

			carroNegocio.tratarInsercaoCarro(carro);

		} catch (CampoObrigatorioCarroException e) {
			Toast.makeText(this, R.string.nomeObrigatorio, Toast.LENGTH_LONG)
					.show();

		}
	}

	/**
	 * Limpa os campos da tela de Carro.
	 */
	private void limparCampos() {

		Toast.makeText(getApplication(), R.string.sucesso, Toast.LENGTH_LONG)
				.show();

		nome.setText("");
		modelo.setText("");
		placa.setText("");

	}

	/**
	 * Recupera informa��es do carro e preenche o objeto.
	 * 
	 * @return
	 */
	private Carro recuperarInformacaoCarro() {

		Carro carro = new Carro(nome.getText().toString(), placa.getText()
				.toString(), modelo.getText().toString());

		return carro;

	}

	/**
	 * Abre a tela de busca.
	 */
	private void abrirTelaBusca() {

		Intent it = new Intent(this, BuscarCarroController.class);
		startActivity(it);

	}

	/**
	 * Abre a tela de exclus�o.
	 */
	private void abrirTelaExclusao() {

		Intent it = new Intent(this, ExcluirCarroController.class);
		startActivity(it);

	}
}