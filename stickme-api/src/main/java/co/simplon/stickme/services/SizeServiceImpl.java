package co.simplon.stickme.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.stickme.dtos.LabelValue;
import co.simplon.stickme.repositories.SizeRepository;

@Service
@Transactional(readOnly = true)
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizes;

    public SizeServiceImpl(SizeRepository sizes) {
	this.sizes = sizes;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return sizes.findAllProjectedByOrderByLogicalOrder();
    }
}
