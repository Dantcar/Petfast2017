/**
 * Utilitarios Diversas funções utilitarias para Java Início: 22/09/2016
 * 
*
 */
package visao;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Arquivo filtro para utilizar no método accptAllFileFilterUsed de FileChooser
 * FileChooser.addChoosableFileFilter(new TextFilter());
 *
 * @version
 * @author Dantcar
 * @since
 */
class TextFilter extends FileFilter {

    public TextFilter() {

    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String s = f.getName();
        int i = s.lastIndexOf('.');

        //filtra para abrir somente arquivos jpg
        if (i > 0 && i < s.length() - 1) {
            if (s.substring(i + 1).toLowerCase().equals("jpg")) {
                return true;
            }
        }

        //filtra para abrir somente arquivos jpeg
        if (i > 0 && i < s.length() - 1) {
            if (s.substring(i + 1).toLowerCase().equals("jpeg")) {
                return true;
            }
        }

        //filtra para abrir somente arquivos jpg
        if (i > 0 && i < s.length() - 1) {
            if (s.substring(i + 1).toLowerCase().equals("png")) {
                return true;
            }
        }

        //filtra para abrir somente arquivos bmp
        if (i > 0 && i < s.length() - 1) {
            if (s.substring(i + 1).toLowerCase().equals("bmp")) {
                return true;
            }
        }

        //filtra para abrir somente arquivos gif
        if (i > 0 && i < s.length() - 1) {
            if (s.substring(i + 1).toLowerCase().equals("gif")) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Aceita somente arquivos de imagem: jpg,jpeg,png,bmp ou gif.";
    }

}
