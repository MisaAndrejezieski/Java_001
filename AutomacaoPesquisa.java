import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AutomacaoPesquisa {
    private static final Logger logger = Logger.getLogger("AutomacaoPesquisaLog");
    private static final List<String> temasEn = List.of(
        "technology", "health", "education", "sports", "politics", "economy",
        "science", "art", "music", "literature", "history", "geography",
        "philosophy", "psychology", "sociology", "anthropology", "astronomy",
        "biology", "chemistry", "physics", "mathematics", "engineering", "medicine",
        "law", "administration", "marketing", "finance", "architecture",
        "design", "fashion", "gastronomy"
    );
    private static final List<String> perguntasEn = List.of(
        "What is %s?", "What are the latest news in %s?", "How does %s impact society?",
        "What are the main challenges in %s?", "Who are the leading experts in %s?"
    );

    public static void main(String[] args) throws Exception {
        // Configuração de logging
        FileHandler fileHandler = new FileHandler("automacao_pesquisa.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

        logger.info("O código de automação de pesquisa no Edge vai começar....");
        if (verificarConectividade()) {
            for (int i = 0; i < 6; i++) {
                String tema = temasEn.get(new Random().nextInt(temasEn.size()));
                List<String> pesquisas = gerarPesquisasSobreTema(tema, 5);

                if (abrirEdge()) {
                    for (String pesquisa : pesquisas) {
                        realizarPesquisa(pesquisa);
                    }
                    limparDadosNavegacao();
                    fecharNavegador();
                } else {
                    logger.severe("Não foi possível abrir o navegador Edge.");
                }
            }
        } else {
            logger.severe("Não foi possível verificar a conectividade com a internet.");
        }
        logger.info("O programa está concluído.");
    }

    private static List<String> gerarPesquisasSobreTema(String tema, int n) {
        List<String> pesquisas = new ArrayList<>();
        for (String p : perguntasEn) {
            pesquisas.add(String.format(p, tema));
        }
        return pesquisas.subList(0, Math.min(n, pesquisas.size()));
    }

    private static boolean abrirEdge() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_G);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_E);
            robot.keyRelease(KeyEvent.VK_E);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            logger.info("Navegador Edge aberto com sucesso.");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao abrir o Edge: " + e.getMessage());
            return false;
        }
    }

    private static void realizarPesquisa(String pesquisa) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(1000);
            for (char c : pesquisa.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException("Key code not found for character '" + c + "'");
                }
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
                robot.delay(100);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(10000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            logger.info("Pesquisa realizada: " + pesquisa);
        } catch (Exception e) {
            logger.severe("Erro ao realizar a pesquisa: " + e.getMessage());
        }
    }

    private static void limparDadosNavegacao() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            logger.info("Dados de navegação e cookies limpos com sucesso.");
        } catch (Exception e) {
            logger.severe("Erro ao limpar os dados de navegação: " + e.getMessage());
        }
    }

    private static void fecharNavegador() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_F4);
            robot.keyRelease(KeyEvent.VK_ALT);
            logger.info("Navegador fechado com sucesso.");
        } catch (Exception e) {
            logger.severe("Erro ao fechar o navegador: " + e.getMessage());
        }
    }

    private static boolean verificarConectividade() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                logger.info("Conectividade com a internet verificada.");
                return true;
            } else {
                logger.severe("Falha na verificação de conectividade com a internet.");
                return false;
            }
        } catch (IOException e) {
            logger.severe("Erro ao verificar a conectividade com a internet: " + e.getMessage());
            return false;
        }
    }
}

