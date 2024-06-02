import java.util.Arrays;

public class Matriz {
    private int linhas;
    private int colunas;
    private double[][] elementos;

    // Construtor
    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.elementos = new double[linhas][colunas];
    }

    // Método para acessar um elemento da matriz
    public double getElemento(int linha, int coluna) {
        return elementos[linha][coluna];
    }

    // Método para definir um elemento da matriz
    public void setElemento(int linha, int coluna, double valor) {
        elementos[linha][coluna] = valor;
    }

    // Método para comparar duas matrizes
    public boolean equals(Matriz outra) {
        if (outra == null || outra.linhas != linhas || outra.colunas != colunas) {
            return false;
        }
        for (int i = 0; i < linhas; i++) {
            if (!Arrays.equals(elementos[i], outra.elementos[i])) {
                return false;
            }
        }
        return true;
    }

    // Método para retornar a transposta da matriz
    public Matriz transposta() {
        Matriz transposta = new Matriz(colunas, linhas);
        for (int i = 0; i < colunas; i++) {
            for (int j = 0; j < linhas; j++) {
                transposta.setElemento(i, j, elementos[j][i]);
            }
        }
        return transposta;
    }

    // Método para retornar a oposta da matriz
    public Matriz oposta() {
        Matriz oposta = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                oposta.setElemento(i, j, -elementos[i][j]);
            }
        }
        return oposta;
    }

    // Método para gerar uma matriz nula
    public static Matriz matrizNula(int linhas, int colunas) {
        Matriz nula = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                nula.setElemento(i, j, 0);
            }
        }
        return nula;
    }

    // Método para verificar se a matriz é identidade
    public boolean ehIdentidade() {
        if (linhas != colunas) {
            return false;
        }
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if ((i == j && elementos[i][j] != 1) || (i != j && elementos[i][j] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para verificar se a matriz é diagonal
    public boolean ehDiagonal() {
        if (linhas != colunas) {
            return false;
        }
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i != j && elementos[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método para verificar se a matriz é singular
    public boolean ehSingular() {
        if (!ehDiagonal()) {
            return false;
        }
        for (int i = 0; i < linhas; i++) {
            if (elementos[i][i] == 0) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar se a matriz é simétrica
    public boolean ehSimetrica() {
        return equals(transposta());
    }

    // Método para verificar se a matriz é anti-simétrica
    public boolean ehAntiSimetrica() {
        return oposta().equals(transposta());
    }

    // Método para somar outra matriz a esta matriz
    public void somar(Matriz outra) {
        if (outra.linhas != linhas || outra.colunas != colunas) {
            throw new IllegalArgumentException("As matrizes devem ter o mesmo tamanho");
        }
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                elementos[i][j] += outra.elementos[i][j];
            }
        }
    }

    // Método para subtrair outra matriz desta matriz
    public void subtrair(Matriz outra) {
        if (outra.linhas != linhas || outra.colunas != colunas) {
            throw new IllegalArgumentException("As matrizes devem ter o mesmo tamanho");
        }
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                elementos[i][j] -= outra.elementos[i][j];
            }
        }
    }

    // Método para multiplicar esta matriz por outra matriz
    public void multiplicar(Matriz outra) {
        if (colunas != outra.linhas) {
            throw new IllegalArgumentException("Número de colunas da primeira matriz deve ser igual ao número de linhas da segunda matriz");
        }
        double[][] resultado = new double[linhas][outra.colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < outra.colunas; j++) {
                for (int k = 0; k < colunas; k++) {
                    resultado[i][j] += elementos[i][k] * outra.elementos[k][j];
                }
            }
        }
        elementos = resultado;
        colunas = outra.colunas;
    }

    // Método para gerar uma cópia da matriz
    public Matriz copia() {
        Matriz copia = new Matriz(linhas, colunas);
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                copia.setElemento(i, j, elementos[i][j]);
            }
        }
        return copia;
    }

    // Método principal para teste
    public static void main(String[] args) {
        Matriz matriz1 = new Matriz(2, 2);
        matriz1.setElemento(0, 0, 1);
        matriz1.setElemento(0, 1, 2);
        matriz1.setElemento(1, 0, 3);
        matriz1.setElemento(1, 1, 4);

        Matriz matriz2 = new Matriz(2, 2);
        matriz2.setElemento(0, 0, 5);
        matriz2.setElemento(0, 1, 6);
        matriz2.setElemento(1, 0, 7);
        matriz2.setElemento(1, 1, 8);

        // Testando operações com matrizes
        System.out.println("Matriz 1:");
        imprimirMatriz(matriz1);

        System.out.println("Matriz 2:");
        imprimirMatriz(matriz2);

        // Verificando se as matrizes são iguais
        System.out.println("As matrizes são iguais? " + matriz1.equals(matriz2));

        // Retornando a transposta da matriz 1
        Matriz transpostaMatriz1 = matriz1.transposta();
        System.out.println("Transposta da Matriz 1:");
        imprimirMatriz(transpostaMatriz1);

        // Retornando a oposta da matriz 2
        Matriz opostaMatriz2 = matriz2.oposta();
        System.out.println("Oposta da Matriz 2:");
        imprimirMatriz(opostaMatriz2);

        // Gerando uma matriz nula de dimensões 3x3
        Matriz matrizNula = Matriz.matrizNula(3, 3);
        System.out.println("Matriz Nula 3x3:");
        imprimirMatriz(matrizNula);

        // Verificando se a matriz 1 é identidade
        System.out.println("A matriz 1 é uma matriz identidade? " + matriz1.ehIdentidade());

        // Verificando se a matriz 1 é diagonal
        System.out.println("A matriz 1 é uma matriz diagonal? " + matriz1.ehDiagonal());

        // Verificando se a matriz 2 é singular
        System.out.println("A matriz 2 é uma matriz singular? " + matriz2.ehSingular());

        // Verificando se a matriz 1 é simétrica
        System.out.println("A matriz 1 é uma matriz simétrica? " + matriz1.ehSimetrica());

        // Verificando se a matriz 2 é anti-simétrica
        System.out.println("A matriz 2 é uma matriz anti-simétrica? " + matriz2.ehAntiSimetrica());

        // Somando a matriz 1 com a matriz 2
        matriz1.somar(matriz2);
        System.out.println("Matriz 1 após somar com a Matriz 2:");
        imprimirMatriz(matriz1);

        // Subtraindo a matriz 2 da matriz 1
        matriz1.subtrair(matriz2);
        System.out.println("Matriz 1 após subtrair a Matriz 2:");
        imprimirMatriz(matriz1);

        // Multiplicando a matriz 1 pela matriz 2
        matriz1.multiplicar(matriz2);
        System.out.println("Matriz 1 após multiplicar pela Matriz 2:");
        imprimirMatriz(matriz1);

        // Gerando uma cópia da matriz 1
        Matriz copiaMatriz1 = matriz1.copia();
        System.out.println("Cópia da Matriz 1:");
        imprimirMatriz(copiaMatriz1);
    }

    // Método auxiliar para imprimir a matriz
    private static void imprimirMatriz(Matriz matriz) {
        for (int i = 0; i < matriz.linhas; i++) {
            for (int j = 0; j < matriz.colunas; j++) {
                System.out.print(matriz.getElemento(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
