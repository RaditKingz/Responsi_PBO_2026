package Responsi;

import java.util.Locale;

public abstract class Pompa {

    public static final String NAMA_SPBU = "SPBU Pertamina Purwokerto";
    public static final double STOK_MINIMAL = 100.0;
    public static final double STOK_AWAL_DEFAULT = 1000.0;

    private final String kodePompa;

    private String namaBahanBakar;
    private double hargaPerLiter;
    private double stokLiter;
    private double totalTerjualLiter;
    private boolean aktif;

    public Pompa(String kodePompa, String namaBahanBakar, double hargaPerLiter, double stokLiter) {
        this.kodePompa = kodePompa;
        this.namaBahanBakar = namaBahanBakar;
        this.hargaPerLiter = hargaPerLiter;
        this.stokLiter = stokLiter;
        this.totalTerjualLiter = 0;
        this.aktif = true;
    }

    public Pompa(String kodePompa, String namaBahanBakar, double hargaPerLiter) {
        this(kodePompa, namaBahanBakar, hargaPerLiter, STOK_AWAL_DEFAULT);
    }

    public abstract boolean layaniPengisian(double liter);

    public void kurangiStok(double liter) {
        this.stokLiter -= liter;
        this.totalTerjualLiter += liter;
    }

    public void isiUlangStok(double liter) {
        this.stokLiter += liter;
        if (this.stokLiter > STOK_MINIMAL) {
            this.aktif = true;
        }
    }

    protected void cekStokMinimal() {
        if (this.stokLiter <= STOK_MINIMAL) {
            this.aktif = false;
            System.out.println("   [!] Stok " + namaBahanBakar + " menipis, pompa " + kodePompa + " dikunci.");
        }
    }

    public void tampilkanInfo() {
        System.out.println("Pompa " + kodePompa + " | " + namaBahanBakar
                + " | Harga: " + rupiah(hargaPerLiter) + "/L"
                + " | Stok: " + stokLiter + " L"
                + " | Status: " + (aktif ? "AKTIF" : "TERKUNCI"));
    }

    public double hitungPendapatan() {
        return totalTerjualLiter * hargaPerLiter;
    }

    protected static String rupiah(double nominal) {
        return "Rp" + String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nominal);
    }

    public String getKodePompa() { return kodePompa; }

    public String getNamaBahanBakar() { return namaBahanBakar; }
    public void setNamaBahanBakar(String namaBahanBakar) { this.namaBahanBakar = namaBahanBakar; }

    public double getHargaPerLiter() { return hargaPerLiter; }
    public void setHargaPerLiter(double hargaPerLiter) { this.hargaPerLiter = hargaPerLiter; }

    public double getStokLiter() { return stokLiter; }
    public void setStokLiter(double stokLiter) { this.stokLiter = stokLiter; }

    public double getTotalTerjualLiter() { return totalTerjualLiter; }
    public void setTotalTerjualLiter(double totalTerjualLiter) { this.totalTerjualLiter = totalTerjualLiter; }

    public boolean isAktif() { return aktif; }
    public void setAktif(boolean aktif) { this.aktif = aktif; }
}
