package com.coinup.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coinup.framework.view.jsf.GenericAbstractController;
import com.coinup.model.Caixa;
import com.coinup.service.CaixaService;

@Controller
@Scope("view")
public class CaixaController extends GenericAbstractController<Caixa, Long, CaixaService> {

	public CaixaController() {}
	
	public void abrirCaixa() {
		getService().abrirCaixa(getEntity());
	}
	
	public void fecharCaixa() {
		getService().fecharCaixa(getEntity());
	}
	
}
