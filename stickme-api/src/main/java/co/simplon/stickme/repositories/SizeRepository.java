package co.simplon.stickme.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.stickme.dtos.LabelValue;
import co.simplon.stickme.entities.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

    Collection<LabelValue> findAllProjectedByOrderByLogicalOrder();
}
