package com.coinup.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coinup.framework.view.jsf.Crud;
import com.coinup.framework.view.jsf.GenericAbstractController;
import com.coinup.model.Caixa;
import com.coinup.service.CaixaService;

@Controller
@Scope("view")
@Crud(listPageLocation="/pages/caixa/list.jsf",
	  formPageLocation="/pages/caixa/form.jsf")
public class CaixaController extends GenericAbstractController<Caixa, Long, CaixaService> {

	public CaixaController() {}
	
	public void criarCaixa() {
		getService().criarCaixa(getEntity());
	}
	
	public void fecharCaixa() {
		getService().fecharCaixa(getEntity());
	}
	
}
