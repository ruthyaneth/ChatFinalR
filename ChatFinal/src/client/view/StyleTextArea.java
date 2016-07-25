package client.view;

import java.net.ServerSocket;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.text.*;
import javax.swing.text.html.*;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.parser.*;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Document;
import java.util.*;

import jsyntaxpane.DefaultSyntaxKit;
/**
 * UNIVERSIDAD PEDAGOGICA Y TECNOLOGICA DE COLOMBIA
 * FACULTAD DE INGENIERIA.
 * ESCUELA DE INGENIERIA DE SISTEMAS Y COMPUTACION.
 * PRESENTADO A: Ing Helver Valero.
 * PROGRAMACION III
 * Clase que  se genera para poder enviar los links, usando la libreria jsoup
 * @author Jenny Quesada , Ruth Rojas
 */
public class StyleTextArea extends JEditorPane {
	
	//------Atributtes------
	
	private HTMLEditorKit team;
	private HTMLDocument document;

	//-----Builder-----
	
	public StyleTextArea() {
		init();
	}
	
	//------Methods-------
	public void init(){
		initThis();
	}
	
	public void initThis(){
		this.team = new HTMLEditorKit();
		this.document = new HTMLDocument();
		this.setCSS();
		this.setEditorKit(team);
		this.document = (HTMLDocument) team.createDefaultDocument();
		this.team.setAutoFormSubmission(true);
		this.setDocument(document);
		this.setEditable(false);
		this.setOpaque(false);
		this.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (Exception ee) {
							System.err.println(ee.getMessage());
						}
					}
				}
			}
		});
		this.appendHTML("<u><> Bienvenidos</a></u>");
	}

	
	public StyleTextArea extractClass(String tagClass) {
		org.jsoup.nodes.Document doc = Jsoup.parse(this.getText());
		org.jsoup.select.Elements links = doc.getElementsByClass(tagClass);
		String other = "";
		for (org.jsoup.nodes.Element link : links) {
			other += link.toString();
			link.remove();
		}
		StyleTextArea textarea = new StyleTextArea();
		textarea.setText(other);
		return textarea;
	}

	public void setCSS() {
		StyleSheet styleSheet = team.getStyleSheet();
		styleSheet.addRule(".hide {text-indent:-9999px; overflow:hidden;}");
		styleSheet.addRule(".system {color: #2E2EFE;}");
		styleSheet.addRule(".secret {color: #FE2E64;}");
		styleSheet.addRule(".normal {color: #424242;}");
		styleSheet
				.addRule("font {font-family: Comic Sans MS; font-size: 20px; font-weight: bold;}");
		styleSheet
				.addRule("a {font-family: Comic Sans MS; font-size: 20px; color: #21610B;}");
	}

	public void setTextFontFamily(String fontName) {
		StyleSheet styleSheet = team.getStyleSheet();
		styleSheet.getStyle("a").removeAttribute(
				CSS.getAttribute("font-family"));
		styleSheet.getStyle("font").removeAttribute(
				CSS.getAttribute("font-family"));
		styleSheet.getStyle("a").addAttribute(StyleConstants.FontFamily,
				fontName);
		styleSheet.getStyle("font").addAttribute(StyleConstants.FontFamily,
				fontName);
		this.updateUI();
	}

	public void setTextFontSize(int fsize) {
		StyleSheet styleSheet = team.getStyleSheet();
		styleSheet.getStyle("a").removeAttribute(CSS.getAttribute("font-size"));
		styleSheet.getStyle("font").removeAttribute(
				CSS.getAttribute("font-size"));
		styleSheet.getStyle("a").addAttribute(StyleConstants.FontSize, fsize);
		styleSheet.getStyle("font")
				.addAttribute(StyleConstants.FontSize, fsize);
		this.updateUI();
	}

	public static  String transferHyperlink(String val) {
		return "<u><a href=\"" + val + "\">" + val
				+ "</a></u>";
	}

	public static  String transferImageHyperlink(String val) {
		int height, width;
		try {
			URL url = new URL(val);
			BufferedImage image = ImageIO.read(url);
			height = image.getHeight();
			width = image.getWidth();
		} catch (Exception e) {
			return "";
		}
		double rate = 500.0 / width;
		height = (int) (height * rate);
		width = (int) (width * rate);
		return "<img src='" + val + "' width='" + width + "' height='" + height
				+ "' alt='" + val + "' class=\"normal\"></img>";
	}

	public void appendHTML(String content) {

		try {
			team.insertHTML(document, document.getLength(), content, 0, 0, null);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void append(String content, int sflag) {
		content = content.replaceAll("&", "&amp;");
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll(" ", "&nbsp;");
		content = content.replaceAll("\"", "&quot;");
		if (sflag == 0) {
			content = "<div class=\"normal\"><font>" + content
					+ "</font></div>";
		} else if (sflag == 1) {
			content = "<div class=\"secret\"><font>" + content
					+ "</font></div>";
		} else {
			content = "<div class=\"system\"><font>" + content
					+ "</font></div>";
		}
		try {
			team.insertHTML(document, document.getLength(), content, 0, 0, null);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
