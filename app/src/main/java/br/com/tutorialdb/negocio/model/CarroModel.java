package br.com.tutorialdb.negocio.model;

import android.content.Context;

import br.com.tutorialdb.R;
import br.com.tutorialdb.exception.CampoObrigatorioCarroException;
import br.com.tutorialdb.exception.GenericoException;
import br.com.tutorialdb.exception.IdInvalidoException;
import br.com.tutorialdb.negocio.entidade.Carro;
import br.com.tutorialdb.persistencia.CarroPersistencia;

/**
 * 
 * @author douglas
 * 
 */
public class CarroModel {

	private Context context;
	private CarroPersistencia carroPersistencia;

	public CarroModel(Context context) {

		this.context = context;
	}

	public void tratarInsercaoCarro(Carro carro)

			throws CampoObrigatorioCarroException {

		if (carro.getNome() == null) {

			throw new CampoObrigatorioCarroException(R.string.nomeObrigatorio);

		} else {

			carroPersistencia = CarroPersistencia.getInstance(context);
			carroPersistencia.inserirCarro(carro);

		}

	}

	public Carro tratarBuscaCarro(String nomeCarro) throws IdInvalidoException {

		if (nomeCarro != null) {

			carroPersistencia = CarroPersistencia.getInstance(context);
			return carroPersistencia.buscarCarro(nomeCarro);

		} else {

			throw new IdInvalidoException("Id inv�lido");
		}

	}

	public void tratarAtualizacaoCarro(Carro carro) throws GenericoException {

		if (carro != null) {

			carroPersistencia = CarroPersistencia.getInstance(context);
			carroPersistencia.atualizarCarro(carro);

		} else {

			throw new GenericoException();
		}
	}

	public void tratarExclusaoCarro(String nomeCarro) throws GenericoException {

		if (nomeCarro != null) {
			carroPersistencia = CarroPersistencia.getInstance(context);
			carroPersistencia.deletarCarro(nomeCarro);
		} else {

			throw new GenericoException();
		}
	}

}
