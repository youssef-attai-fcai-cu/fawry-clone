package eg.edu.cu.fcai.swe.fawry.wallet.repository;

import eg.edu.cu.fcai.swe.fawry.common.repository.WalletRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryWalletRepository implements WalletRepository {
    List<Wallet> wallets = new ArrayList<>();

    @Override
    public Wallet createWallet(String userId) {
        Wallet newWallet = new Wallet(userId, 0.0f);
        wallets.add(newWallet);
        return newWallet;
    }

    @Override
    public Float getUserBalance(String userId) {
        return wallets.stream().filter(wallet -> wallet.userId().equals(userId)).findFirst().map(Wallet::balance).orElse(null);
    }

    @Override
    public Float updateUserBalance(String userId, Float amount) {
        for (int i = 0; i < wallets.size(); i++) {
            Wallet w = wallets.get(i);
            if (w.userId().equals(userId)) {
                wallets.set(i, new Wallet(w.userId(), w.balance() + amount));
                return wallets.get(i).balance();
            }
        }
        return null;
    }
}
