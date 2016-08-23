-- DROP SCHEMA coinup;
CREATE SCHEMA coinup AUTHORIZATION postgres;

-- drop table coinup.caixa
CREATE TABLE coinup.caixa (
   id bigserial,
   saldo numeric(12,2),
   vl_total_entrada numeric(12,2),
   vl_total_saida numeric(12,2),
   qtd_entradas int,
   qtd_saidas int,
   dt_ultima_atualizacao date,
   PRIMARY KEY (id)
);

-- drop table coinup.tipo_movimentacao
CREATE TABLE coinup.tipo_movimentacao (
   id bigserial,
   label character varying,
   descricao character varying,
   PRIMARY KEY (id)
);

-- drop table coinup.pessoa
CREATE TABLE coinup.pessoa (
   id bigserial, 
   cpf_cnpj character varying, 
   nome character varying,
   razao_social character varying,
   pessoa_juridica boolean,
   observacao character varying,
   PRIMARY KEY (id)
);

-- drop table coinup.tipo_fonte
CREATE TABLE coinup.tipo_fonte (
   id bigserial,
   label character varying,
   descricao character varying,
   PRIMARY KEY (id)
);

-- drop table coinup.fonte
CREATE TABLE coinup.fonte (
   id bigserial,
   saldo numeric(12,2),
   descricao character varying,
   dt_cadastro date,
   dt_criacao date,
   cd_tipo_fonte bigint,
   PRIMARY KEY (id)
);

-- drop table coinup.banco
CREATE TABLE coinup.banco (
   id bigserial,
   codigo integer,
   descricao character varying,
   PRIMARY KEY (id)
);

-- drop table coinup.fonte_conta_bancaria
CREATE TABLE coinup.fonte_conta_bancaria (
   id bigserial,
   numero int,
   agencia int,
   digito_conta char,
   digito_agencia char,
   cd_banco bigint,
   cd_fonte bigint,
   PRIMARY KEY (id)
);

-- drop table coinup.bandeira_cartao
CREATE TABLE coinup.bandeira_cartao(
   id bigserial,
   label character varying,
   descricao character varying,
   PRIMARY KEY (id)
);

-- drop table coinup.fonte_cartao_credito
CREATE TABLE coinup.fonte_cartao_credito (
   id bigserial,
   limite numeric(12,2),
   disponivel_saque numeric(12,2),
   cd_bandeira bigint,
   cd_fonte bigint,
   PRIMARY KEY (id)
);

-- drop table coinup.fonte_clt
CREATE TABLE coinup.fonte_clt (
   id bigserial,
   salario numeric(12,2),
   cd_pessoa bigint,
   cd_fonte bigint,
   PRIMARY KEY (id)
);

-- drop table coinup.movimentacao_financeira
CREATE TABLE coinup.movimentacao_financeira (
   id bigserial, 
   valor numeric(12,2), 
   dt_vencimento_ultima_parcela date, 
   qtd_parcelas integer, 
   quitada boolean, 
   descricao character varying,
   cd_tipo_movimentacao bigint, 
   cd_pessoa bigint, 
   cd_fonte bigint, 
   PRIMARY KEY (id)
);

ALTER TABLE coinup.fonte
  ADD FOREIGN KEY (cd_tipo_fonte) 
  REFERENCES coinup.tipo_fonte (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE coinup.movimentacao_financeira
  ADD FOREIGN KEY (cd_tipo_movimentacao) 
  REFERENCES coinup.tipo_movimentacao (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE coinup.fonte_conta_bancaria
  ADD FOREIGN KEY (cd_fonte) 
  REFERENCES coinup.fonte (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE coinup.fonte_conta_bancaria
  ADD FOREIGN KEY (cd_banco) 
  REFERENCES coinup.banco (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;    

ALTER TABLE coinup.fonte_cartao_credito
  ADD FOREIGN KEY (cd_fonte) 
  REFERENCES coinup.fonte (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;    

ALTER TABLE coinup.fonte_cartao_credito
  ADD FOREIGN KEY (cd_bandeira) 
  REFERENCES coinup.bandeira_cartao (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE coinup.fonte_clt
  ADD FOREIGN KEY (cd_fonte) 
  REFERENCES coinup.fonte (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;
  
ALTER TABLE coinup.fonte_clt
  ADD FOREIGN KEY (cd_pessoa) 
  REFERENCES coinup.pessoa (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE coinup.movimentacao_financeira
  ADD FOREIGN KEY (cd_tipo_movimentacao) 
  REFERENCES coinup.tipo_movimentacao (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE coinup.movimentacao_financeira
  ADD FOREIGN KEY (cd_pessoa) 
  REFERENCES coinup.pessoa (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;  

ALTER TABLE coinup.movimentacao_financeira
  ADD FOREIGN KEY (cd_fonte) 
  REFERENCES coinup.fonte (id) 
  ON UPDATE RESTRICT ON DELETE RESTRICT;

