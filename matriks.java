import java.util.Scanner;
import java.io.*;
import java.math.*;

public class matriks {
	//KAMUS CLASS
	private int NB; //Baris efektif
	private int NK; //Kolom efektif
	private double[][] ele; //Elemen matriks
	
	public matriks(int NB, int NK) { //Konstruktor
		//ALGORITMA
		this.NB = NB;
		this.NK = NK;
		ele = new double[NB+1][NK+1];
	}
	
	public static matriks bacaMatriks() {
		/* Ukuran matriks belum terdefinisi, mendefinisikan ukuran */
		//KAMUS LOKAL
		int NB, NK;
		matriks m;
		Scanner in = new Scanner(System.in);
		
		//ALGORITMA
		NB = in.nextInt();
		NK = in.nextInt();
		if ((NB >= 0) && (NK >= 0))
			m = new matriks(NB, NK);
		else {
			System.out.println("Ukuran tidak sesuai.");
			m = null;
		}
		return m;
	}
	
	public void bacaIsiMatriks() {
		/* Ukuran matriks sudah terdefinisi, mendefinisikan isi elemen */
		//KAMUS LOKAL
		int i, j;
		Scanner in = new Scanner(System.in);
		
		//ALGORITMA
		for (i = 1; i <= this.NB; i++)
			for (j = 1; j <= this.NK; j++)
				this.ele[i][j] = in.nextDouble();
	}
	
	public static matriks bacaFileMatriks(String c) {
		/* Membaca matriks dari file dengan konfigurasi tertentu */
		//KAMUS LOKAL
		File fIn = new File(c);
		Scanner in;
		int NB, NK;
		matriks g = null;
		int i, j;
		
		//ALGORITMA
		try {
			in = new Scanner(fIn);
			while (!in.hasNextInt())
				in.next();
			NB = in.nextInt();
			while (!in.hasNextInt())
				in.next();
			NK = in.nextInt();
			
			g = new matriks(NB, NK);
			for (i = 1; i <= NB; i++) {
				for (j = 1; j <= NK; j++) {
					g.ele[i][j] = (in.nextDouble());
				}
				in.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File tidak ada.");
		} catch (Exception e) {
			System.out.println("Format file tidak benar.");
			g = null;
		}
		return g;
	}
	
	public void simpanMatriks(String c) {
		//KAMUS LOKAL
		File fOut = new File(c);
		PrintWriter out;
		int i, j;
		
		//ALGORITMA
		try {
			out = new PrintWriter(new FileWriter(fOut, false));
			out.printf("Matriks berukuran %d X %d%n", this.NB, this.NK);
			for (i = 1; i <= this.NB; i++) {
				for (j = 1; j <= this.NK; j++) {
					out.printf("%12.6f ", this.ele[i][j]);
				}
				out.printf("#%n");
			}
			out.printf("%n//Tambah komentar atau apapun disini");
			out.close();
		} catch (Exception e) {
			System.out.println("Error.");
		}
	}
	
	public void tulisMatriks() {
		/* Menulis matriks dengan desimal 2 digit belakang koma */
		//KAMUS LOKAL
		int i, j;
		
		//ALGORITMA
		for (i = 1; i <= this.NB; i++) {
			for (j = 1; j <= this.NK; j++) {
				if (this.ele[i][j] == 0)
					this.ele[i][j] = 0;
				System.out.printf("%8.2f", this.ele[i][j]);
				if (j != this.NK)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void tulisMatriksPrecise() {
		/* Menulis matriks dengan desimal 6 digit belakang koma */
		//KAMUS LOKAL
		int i, j;
		
		//ALGORITMA
		for (i = 1; i <= this.NB; i++) {
			for (j = 1; j <= this.NK; j++) {
				if (this.ele[i][j] == 0)
					this.ele[i][j] = 0;
				System.out.printf("%12.6f", this.ele[i][j]);
				if (j != this.NK)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static matriks salinMatriks(matriks o) {
		/* Membuat matriks bernilai sama dengan matriks o */
		//KAMUS LOKAL
		int i, j;
		matriks m;
		
		//ALGORITMA
		m = new matriks(o.NB, o.NK);
		for (i = 1; i <= o.NB; i++)
			for (j = 1; j <= o.NK; j++)
					m.ele[i][j] = o.ele[i][j];
		return m;
	}
	
	public matriks tambahMatriks(matriks m) { //Tak wajib
		/* Menambah matriks ini dengan matriks m */
		//KAMUS LOKAL
		int i, j;
		matriks P;
		
		//ALGORITMA
		P = new matriks(this.NB, this.NK);
		for (i = 1; i <= this.NB; i++)
			for (j = 1; j <= this.NK; j++)
				P.ele[i][j] = this.ele[i][j] + m.ele[i][j];
		return P;
	}
	
	public int rowMostZero(int r1, int rn) {
		/* Mengembalikan baris dengan 0 terbanyak pada matriks.
		 * Jika sama banyak, yang dikembalikan adalah baris terawal */
		//KAMUS LOKAL
		int i, j, rowMost, nZero, countZero;
		
		//ALGORITMA
		nZero = 0;
		rowMost = r1;
		for (i = r1; i <= rn; i++) {
			countZero = 0;
			for (j = 1; j <= this.NK; j++)
				if (this.ele[i][j] == 0)
					countZero++;
			if (countZero > nZero) {
				rowMost = i;
				nZero = countZero;
			}
		}
		return rowMost;
	}
	
	public boolean isRowOneGaussElement(int i) {
		/* Mengecek apakah suatu baris hanya memiliki 2 elemen, salah
		 * satunya berada di kolom terakhir */
		//KAMUS LOKAL
		int j;
		boolean one, check;
		
		//ALGORITMA
		one = false;
		check = false;
		for (j = 1; j < this.NK; j++) {
			if (check) {
				if (this.ele[i][j] != 0)
					one = false;
			} else
				if (this.ele[i][j] != 0) {
					one = true;
					check = true;
			}
		}
		return one;
	}
	
	public int columnMostZero(int c1, int cn) { //tak wajib
		//KAMUS LOKAL
		int i, j, columnMost, nZero, countZero;
		
		//ALGORITMA
		nZero = 0;
		columnMost = c1;
		for (j = c1; j <= cn; j++) {
			countZero = 0;
			for (i = 1; i <= this.NB; i++)
				if (this.ele[i][j] == 0)
					countZero++;
			if (countZero > nZero) {
				columnMost = j;
				nZero = countZero;
			}
		}
		return columnMost;
	}
	
	public matriks rowSwitch(int a, int b) {
		/* Mengubah matriks dengan menukarkan barus a dan baris b */
		//KAMUS LOKAL
		int j;
		double temp;
		matriks s = this;
		
		//ALGORITMA
		for (j = 1; j <= s.NK; j++) {
			temp = s.ele[a][j];
			s.ele[a][j] = s.ele[b][j];
			s.ele[b][j] = temp;
		}
		return s;
	}
	
	public boolean validGaussMatriks() {
		/* Mengecek apakah matriks sesuai untuk operasi eliminasi Gauss Jordan */
		//ALGORITMA
		if (this.NK > this.NB)
			return true;
		else
			return false;
	}
	
	public matriks pivotOperation(int x, int y) {
		/* Melakukan bagian operasi pivoting */
		//KAMUS LOKAL
		int i, j;
		matriks o = this;
		matriks p = this;
		
		//ALGORITMA
		for (i = 1; i <= this.NB; i++)
			if (i != x)
				for (j = 1; j <= this.NK; j++)
					if (j != y)
						o.ele[i][j] = p.ele[i][j] * p.ele[x][y] - p.ele[x][j] * p.ele[i][y];
		for (i = 1; i <= this.NB; i++)
			if (i != x)
				o.ele[i][y] = 0;
		return o;
	}
	
	public matriks pivotEliminasi() {
		/* Menghasilkan matriks yang telah melalui eliminasi Gauss Jordan
		 * dan disederhanakan sehingga terdapat leading 1 */
		//KAMUS LOKAL
		int i; int j;
		int k, l;
		matriks M = this;
		
		//ALGORITMA
		i = 1;
		while (i <= M.NB) {
			l = 1;
			while ((M.rowMostZero(i, M.NB) == i) && (i + l <= M.NB)) {
				M.rowSwitch(i+l, i);
				l++;
			}
			for (j = 1; j <= M.NB; j++) {
				k = 0;
				while ((M.ele[j][1+k] == 0) && (1+k < M.NK)) 
					k++;
				if (1+k < M.NK) {
					for (l = k+2; l <= M.NK; l++)
						M.ele[j][l] = M.ele[j][l] / M.ele[j][1+k];
					M.ele[j][1+k] = 1;
				}
			}
			k = 0;
			if (i+k < M.NK)
				while ((M.ele[i][i+k] == 0) && (i+k < M.NK)) 
					k++;
			if (i + k < M.NK) {
				M = M.pivotOperation(i, i+k);
			}
			
			i++;
		}
		
		//Simplifikasi agar kolom terawal sebuah baris bernilai 1
		for (i = 1; i <= M.NB; i++) {
			k = 0;
			while ((M.ele[i][i+k] == 0) && (i+k < M.NK)) 
				k++;
			if (i+k < M.NK) {
				for (l = i+k+1; l <= M.NK; l++)
					M.ele[i][l] = M.ele[i][l] / M.ele[i][i+k];
				M.ele[i][i+k] = 1;
			}
		}
		return M;
	}
	
	public void pivotResult() {
		/* Mengeluarkan solusi dari matriks Gauss Jordan */
		//KAMUS LOKAL
		matriks m = salinMatriks(this);
		int i;
		boolean rowZero = true;
		
		//ALGORITMA
		if (validGaussMatriks()) {
			m = m.pivotEliminasi();
			if (m.NK == m.NB + 1) {
				i = 1;
				while ((m.ele[m.NB][i] == 0) && (i < m.NK))
					i++;
				if (i == m.NK)
					rowZero = true;
				else
					rowZero = false;
				if (!rowZero) {
					
					//Ada solusi
					System.out.print("Solusi sistem adalah: {");
					for (i = 1; i <= m.NB; i++){
						if (i > 1)
							System.out.print(", ");
						if (m.NB > 5)
							if (i%4 == 0)
								System.out.println();
						System.out.printf("%.2f", m.ele[i][m.NK]);
					}
					System.out.println("}");
					
				}
				
				else if (m.ele[m.NB][m.NK] != 0) {
					
					//No solution
					System.out.println("Tak ada solusi.");
					
				}
			}
			
			//Banyak solusi
			
			if (((m.ele[m.NB][m.NK] == 0) && (rowZero)) || (m.NK > m.NB + 1)) {
				//KAMUS TAMBAHAN
				boolean[] check = new boolean[m.NK];
				boolean[] result = new boolean[m.NK];
				int[] para;
				int count, nCount, j, k, l;
				
				System.out.println("Jumlah solusi tak hingga.");
				nCount = 0;
				
				//Mengisi variabel variabel yang ternyata ada hasilnya
				for (i = 1; i < m.NK; i++)
					result[i] = false;
					
				for (i = 1; i <= m.NB; i++) {
					if (m.isRowOneGaussElement(i)) {
						k = 0;
						while (m.ele[i][1+k] == 0)
							k++;
						result[1+k] = true;
					}
				}
				
				//Mengecek variabel-variabel bebas
				for (i = 1; i < m.NK; i++)
					check[i] = false;
				for (i = 1; i <= m.NB; i++) {
					k = 0;
					while ((m.ele[i][i+k] == 0) && (i+k < m.NK))
						k++;
					j = i + k + 1;
					while (j < m.NK) {
						if (!result[j])
							if (!(check[j]) && (m.ele[i][j] != 0))
								if (!check[j]) {
									check[j] = true;
									nCount++;
								}
						j++;
					}
				}
				
				para = new int[nCount + 1];
				nCount = 1;
				for (i = 1; i < m.NK; i++)
					if (check[i]) {
						para[nCount] = i;
						nCount++;
					}
					
				System.out.print("Solusi sistem adalah: {");
				k = 0;
				for (i = 1; i <= m.NB; i++) {
					
					if ((i > 1) && (i+k < m.NK))
						System.out.print(", ");
						
					//Ketemu 0
					if (i+k < m.NK)
						while ((m.ele[i][i+k] == 0) && (i+k < m.NK)) {
							//Pencarian variabel parametrik
							count = 1;
							while (para[count] != (i+k))
								count++;
							System.out.print((char)('u' - (nCount - count)));
							//end pencarian
							if (i+k != (m.NK - 1))
								System.out.print(", ");
							k++;
						}
					
					//Ketemu bilangan tak 0 atau di kolom akhir
					if (i+k < m.NK) {
						
						//Print elemen pertama tak 0
						if (result[i+k]) {
							System.out.printf("%.2f", m.ele[i][m.NK]);
						} else {
							l = 1;
							while (m.ele[i][i+k+l] == 0)
								l++;
							System.out.printf("%.2f", 0 - m.ele[i][i+k+l]);
							if (i+k + l < m.NK) {
								//Pencarian variabel parametrik
								count = 1;
								while (para[count] != (i+k + l))
									count++;
								System.out.print((char)('u' - (nCount - count)));
								//end pencarian
							}
							
							//Print elemen berikutnya
							j = i+k + l + 1;
							while (j <= m.NK) {
								if (m.ele[i][j] != 0) {
									if (j == m.NK) { //elemen terakhir
										if (m.ele[i][j] > 0)
											System.out.print('+');
										System.out.printf("%.2f", m.ele[i][j]);
									} else {
										if (m.ele[i][j] < 0)
											System.out.print('+');
										System.out.printf("%.2f", 0 - m.ele[i][j]);
										//Pencarian variabel parametrik
										count = 1;
										while (para[count] != j)
											count++;
										System.out.print((char)('u' - (nCount - count)));
										//end pencarian
									}
								}
								j++;
							}
							
						}
					}
				}
				System.out.println('}');
			}
		} else
			System.out.println("Matriks bukan matriks yang sesuai untuk operasi Eliminasi Gauss-Jordan.");
	}
	
	public boolean validInterpolasiMatriks(int n) {
		/* Mengecek apakah matriks sesuai untuk perhitungan interpolasi
		 * berderajat n */
		//ALGORITMA
		if ((this.NK == 2) && (this.NB > n))
			return true;
		else
			return false;
	}
	
	public double interpolasiOperasi(double x, int n) {
		/* Melakukan sebagian dari proses interpolasi (rekursif) */
		//KAMUS LOKAL
		double result;
		int i;
		matriks p;
		
		//ALGORITMA
		result = 0;
		p = new matriks(this.NB, 2);
		for (i = 1; i <= p.NB; i++)
			p.ele[i][1] = this.ele[i][1];
		for(i = 1; i <= n; i++)
			p.ele[i][2] = (this.ele[i+1][2] - this.ele[i][2]) / (this.ele[i+(p.NB - n)][1] - this.ele[i][1]);
		
		if (n > 1)
			result = p.interpolasiOperasi(x, n-1);
		else
			result = p.ele[1][2];
		return result;
	}
	
	public void interpolasiResult(double x, int n) {
		/* Melakukan operasi interpolasi lalu menulis hasilnya */
		//KAMUS LOKAL
		double result, product;
		int i, k;
		matriks temp = salinMatriks(this);
		
		//ALGORITMA
		if (validInterpolasiMatriks(n)) {
			result = 0;
			System.out.println("Persamaan interpolasinya adalah: ");
			for (i = 1; i <= n+1; i++) {
				k = i;
				product = 1;
				while (k > 1) {
					product = product * (x - this.ele[k-1][1]);
					k--;
				}
				if (i == 1) {
					result = result + this.ele[1][2];
					System.out.print(this.ele[1][2]);
				} else {
					temp.NB = i;
					result = result + product*(temp.interpolasiOperasi(x, i-1));
					System.out.printf("%n+ ");
					k = i;
					while (k > 1) {
						System.out.printf("(x - %.2f)", this.ele[k-1][1]);
						k--;
					}
					System.out.printf("(%.6f)", temp.interpolasiOperasi(x, i-1));
				}
			}
			System.out.printf("%nHasil f(%.2f) berderajat %d adalah : ", x, n);
			System.out.println(result);
		} else
			System.out.println("Matriks bukan matriks yang sesuai untuk perhitungan interpolasi ini.");
	}

	
	public static void main(String []args) {
		/* Bagian program untuk melakukan operasi matriks */
		//KAMUS
		matriks A = null;
		matriks B = null;
		Scanner input = new Scanner(System.in);
		String x;
		double a;
		int n, i, j;
		
		//ALGORITMA
		System.out.println("Program tersebut bertujuan untuk melakukan operasi pada matriks.");
		System.out.println("Masukkan 0 untuk help, 000 untuk keluar.");
		do {
			System.out.print("Kode: ");
			x = input.next();
			System.out.println();
			switch (x) {
				
				case "1" : {
					System.out.println("Input size matriks m dan n, lalu input isinya: ");
					B = bacaMatriks();
					if (B == null)
						System.out.println("Matriks gagal disimpan.");
					else {
						System.out.println("Input elemen-elemen matriks:");
						B.bacaIsiMatriks();
						System.out.println();
						A = B;
						System.out.println("Matriks berhasil disimpan.");
					}
					break;
				}
				
				case "2" : {
					System.out.print("Masukkan nama file untuk membaca matriks: ");
					B = bacaFileMatriks(input.next());
					if (B == null)
						System.out.println("Matriks gagal dibaca.");
					else {
						A = B;
						System.out.println("Matriks berhasil dibaca.");
					}
					break;
				}
				
				case "3" : {
					if (A == null) {
						System.out.println("Matriks belum terdefinisi.");
					} else {
						System.out.println("Pilih:");
						System.out.println("0. presisi 2 desimal");
						System.out.println("1. presisi 6 desimal");
						x = input.next();
						while ((!x.equals("1")) && (!x.equals("0"))) {
							System.out.println("Masukkan input yang sesuai.");
							x = input.next();
						}
						if (x.equals("0"))
							A.tulisMatriks();
						else //x.equals("1")
							A.tulisMatriksPrecise();
					}
					break;
				}
				
				case "4" : {
					A.pivotResult();
					break;
				}
				
				case "5" : {
					try {
						if (A.NK != 2)
							System.out.println("Matriks tidak sesuai untuk interpolasi.");
						else {
							System.out.print("Masukkan titik x: ");
							a = input.nextDouble();
							System.out.print("Masukkan derajat: ");
							n = input.nextInt();
							A.interpolasiResult(a, n);
						}
					} catch (Exception e) {
						System.out.println("Terjadi error. Proses dihentikan.");
					}
					break;
				}
				
				case "6" : {
					System.out.print("Masukkan nama file untuk menyimpan matriks: ");
					A.simpanMatriks(input.next());
					System.out.println("Matriks berhasil disimpan.");
					break;
				}
				
				case "7" : {
					try {
						System.out.print("Masukkan n: ");
						n = input.nextInt();
						A = new matriks(n, n+1);
						for (i = 1; i <= A.NB; i++) {
							for (j = 1; j <= (A.NK-1); j++) {
								A.ele[i][j] = (1.0 / (i+j-1));
							}
						}
						for (i = 1; i <= A.NB; i++)
							A.ele[i][A.NK] = 1;
						System.out.println("Matriks berhasil disimpan."); 
					} catch (Exception e) {
						System.out.println("Terjadi error. Proses dihentikan.");
					}
					break;
				}
				
				
				case "0" : {
					System.out.println("0. Help");
					System.out.println("1. Baca matriks (keyboard)");
					System.out.println("2. Baca matriks (file)");
					System.out.println("3. Tulis matriks");
					System.out.println("4. Mencari solusi matriks (Pivoting Gauss-Jordan)");
					System.out.println("5. Mencari solusi dari matriks interpolasi");
					System.out.println("6. Menyimpan matriks ke sebuah file.");
					System.out.println("7. Membuat matriks Hilbert ukuran n.");
					System.out.println("000. Exit");
					break;
				}
				case "000" : break;
				default : {
					System.out.println("Masukkan input yang sesuai.");
				}
			}
			System.out.println();
		} while (!x.equals("000"));
	}
}
