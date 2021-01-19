package markt_study.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import markt_study.client.ClientImpl;
import markt_study.deployment.DeployImpl;
import markt_study.deployment.Deployment;
import markt_study.participation.Participation;
import markt_study.participation.ParticipationImpl;
import markt_study.study.Study;
import markt_study.study.StudyImpl;


@Service
public class DeploymentPdfService {

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
	 * contructor init the service of study and template engime
	 * @param studyImpl study service
	 * @param templateEngine template engime
	 */
    @Autowired
    public DeploymentPdfService(StudyImpl studyImpl, SpringTemplateEngine templateEngine) {
        this.studyImpl = studyImpl;
        this.templateEngine = templateEngine;
    }
    
    /**
     * generate the pdf file
     * @param id identity of deploy in database
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
    * method to render html to pdf
    * @param html html containt as String
    * @return return a file
    * @throws IOException
    * @throws DocumentException
    */
    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("deployment", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }
    
    /**interface Context to save all element of study
     * 
     * @param id idenify of deploy
     * @return a context obect
     */

    private Context getContext(Long id) {
        Context context = new Context();
		Deployment deploy = deployImpl.get(id);
        context.setVariable("questioner", deploy.getQuestionner());
        context.setVariable("study", deploy.getStudy());
        context.setVariable("deploy", deploy);
        return context;
    }

      /**
       * method to load the html template
       * @param context context object
       * @return return a string object
       */
    private String loadAndFillTemplate(Context context) {
    	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine.process("templates/deploymentpdf_template", context);
    }
    
}

