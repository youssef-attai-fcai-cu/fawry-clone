package eg.edu.cu.fcai.swe.fawry.common.repository;

import eg.edu.cu.fcai.swe.fawry.wallet.model.Wallet;

public interface WalletRepository {
    Wallet createWallet(String userId);
    Float getUserBalance(String userId);
    Float updateUserBalance(String userId, Float amount);
}
