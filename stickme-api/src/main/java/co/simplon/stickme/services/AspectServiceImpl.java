package co.simplon.stickme.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.stickme.dtos.LabelValue;
import co.simplon.stickme.repositories.AspectRepository;

@Service
@Transactional(readOnly = true)
public class AspectServiceImpl implements AspectService {

    private final AspectRepository aspects;

    public AspectServiceImpl(AspectRepository aspects) {
	this.aspects = aspects;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return aspects.findAllProjectedByOrderByLogicalOrder();
    }
}
