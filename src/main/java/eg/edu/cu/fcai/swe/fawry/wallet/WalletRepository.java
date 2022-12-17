package eg.edu.cu.fcai.swe.fawry.wallet;

public interface WalletRepository {
    Wallet createWallet(String userId);
    Float getUserBalance(String userId);
    Float updateUserBalance(String userId, Float amount);
}
