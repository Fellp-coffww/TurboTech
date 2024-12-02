package org.com.br.Application.Desktop.View;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.com.br.Core.Domain.Models.OrdemServico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class RelatorioOrdemServicoPDF {

    public void gerarRelatorio(List<OrdemServico> ordensServico, String caminhoArquivo) {
        try {
            // Criar o arquivo PDF
            PdfWriter writer = new PdfWriter(caminhoArquivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Adicionar título ao relatório
            Paragraph titulo = new Paragraph("Relatório de Ordens de Serviço")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            // Espaço entre título e tabela
            document.add(new Paragraph("\n"));

            // Criar tabela com colunas fixas
            float[] columnWidths = {1, 2, 2, 2, 2, 2};
            Table table = new Table(UnitValue.createPercentArray(columnWidths))
                    .useAllAvailableWidth();

            // Cabeçalhos da tabela
            table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Data")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Status")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Preço Total")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Preço Pago")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Placa")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());

// Preencher tabela com os dados das ordens de serviço
            for (OrdemServico os : ordensServico) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(os.getIdOrdemServico()))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(os.getData()))));
                table.addCell(new Cell().add(new Paragraph(os.getStatusOS())));
                table.addCell(new Cell().add(new Paragraph(String.format("R$ %.2f", os.getPrecoTotal()))));
                table.addCell(new Cell().add(new Paragraph(String.format("R$ %.2f", os.getPrecoPago()))));
                table.addCell(new Cell().add(new Paragraph(os.getPlaca())));
            }

            // Adicionar tabela ao documento
            document.add(table);

            // Fechar documento
            document.close();
            System.out.println("Relatório gerado com sucesso: " + caminhoArquivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

}
