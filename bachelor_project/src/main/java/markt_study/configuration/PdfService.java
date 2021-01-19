package markt_study.configuration;

import com.lowagie.text.DocumentException;

import markt_study.client.ClientImpl;
import markt_study.deployment.DeployImpl;
import markt_study.deployment.Deployment;
import markt_study.participation.Participation;
import markt_study.participation.ParticipationImpl;
import markt_study.study.Study;
import markt_study.study.StudyImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    
    private SpringTemplateEngine templateEngine;
    
	@Autowired
	private StudyImpl studyImpl;
	
	@Autowired
	private ClientImpl clientImpl;

	@Autowired
	private ParticipationImpl partImpl;
	
	@Autowired
	private DeployImpl deployImpl;

	 /**
	  * Constructor init the services
	  * @param studyImpl study service
	  * @param templateEngine template engine
	  */
    @Autowired
    public PdfService(StudyImpl studyImpl, SpringTemplateEngine templateEngine) {
        this.studyImpl = studyImpl;
        this.templateEngine = templateEngine;
    }
    
    /**
     * methode to generate pdf file
     * @param id identify of study
     * @return return a file
     * @throws IOException
     * @throws DocumentException
     */
    public  File generatePdf(Long id) throws IOException, DocumentException {
        Context context = getContext(id);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }
      
    /**
     * method to render pdf file
     * @param html html containt as string
     * @return return a file
     * @throws IOException
     * @throws DocumentException
     */

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("study", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }
   
    /**
     * interface context
     * @param id study id
     * @return return a context
     */
    private Context getContext(Long id) {
        Context context = new Context();
        Study data = studyImpl.get(id);
		List<Participation> parti = partImpl.findByStudy(data);
		List<Deployment> deploy = deployImpl.findByStudy(data);
        context.setVariable("participation", parti);
        context.setVariable("data", data);
        context.setVariable("deploy", deploy);
        return context;
    }

      /**
       * load the context in the template
       * @param context
       * @return return a string
       */
    private String loadAndFillTemplate(Context context) {
    	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);



        return templateEngine.process("templates/studypdf_template", context);
    }


}