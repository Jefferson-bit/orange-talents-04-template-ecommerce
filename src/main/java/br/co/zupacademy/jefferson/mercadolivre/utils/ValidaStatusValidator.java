package br.co.zupacademy.jefferson.mercadolivre.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.co.zupacademy.jefferson.mercadolivre.enums.StatusDaCompra;

public class ValidaStatusValidator implements ConstraintValidator<ValidaStatus, StatusDaCompra>{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean isValid(StatusDaCompra value, ConstraintValidatorContext context) {
		Boolean valid = manager.createQuery("SELECT count(1) > 0 FROM Transacao WHERE status = :status",
				Boolean.class)
	    .setParameter("status", value)
	    .getSingleResult();

		return !valid;
	}

}
