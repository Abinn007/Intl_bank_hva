package hva.c19.int_bank_of_hva.service;

import hva.c19.int_bank_of_hva.model.ConfirmationToken;
import hva.c19.int_bank_of_hva.repositories.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        super();
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ConfirmationToken getByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }
}
