package co.edu.unisabana.parcial.service;

import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTestChechpointDAO {
    @Mock
    private CheckpointRepository checkpointRepository;

    @Test
    public void Given_chekin_When_invoke_saveChekin_Then_save_chekin() {

        Checkin chekin = new Checkin("Chevrolette", "Juan", 15);

        given(checkpointRepository.save(Mockito.any(Checkpoint.class))).willReturn(Checkpoint.fromCheckin(chekin));

        Checkpoint resultado = checkpointRepository.save(Checkpoint.fromCheckin(chekin));

        assertEquals(resultado.getFacility(), chekin.getFacility());

        Mockito.verify(checkpointRepository).save(Mockito.any(Checkpoint.class));

    }

    @Test
    public void Given_checkout_When_invoke_saveCheckout_Then_save_checkout() {

        Checkout checkout = new Checkout("Chevrolette", "Juanita", 16);

        given(checkpointRepository.save(Mockito.any(Checkpoint.class))).willReturn(Checkpoint.fromCheckout(checkout));

        Checkpoint resultado = checkpointRepository.save(Checkpoint.fromCheckout(checkout));

        assertEquals(resultado.getFacility(), checkout.getFacility());

        Mockito.verify(checkpointRepository).save(Mockito.any(Checkpoint.class));

    }

    @Test
    public void Given_driver_and_facility_When_invoke_findLastCheckin_Then_return_last_checkin() {

        Checkin chekin = new Checkin("Chevrolette", "Juan", 15);

        given(checkpointRepository.save(Mockito.any(Checkpoint.class))).willReturn(Checkpoint.fromCheckin(chekin));

        Optional<Checkpoint> checkpoint = Optional.of(checkpointRepository.save(Checkpoint.fromCheckin(chekin)));

        when(checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("Juan", "Chevrolette")).
                thenReturn(checkpoint);


        Optional<Checkpoint> resultado = checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("Juan", "Chevrolette");

        assertEquals(checkpoint, resultado);
    }
}
