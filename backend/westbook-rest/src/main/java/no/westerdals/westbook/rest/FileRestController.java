package no.westerdals.westbook.rest;

import no.westerdals.westbook.uploads.DownloadRequest;
import no.westerdals.westbook.MessageConstant;
import no.westerdals.westbook.model.FileMeta;
import no.westerdals.westbook.responses.ResultResponse;
import static no.westerdals.westbook.responses.ResultResponse.*;

import no.westerdals.westbook.uploads.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
public class FileRestController
{
    @Autowired
    UploadService uploadService;

    @RequestMapping(value="/rest/v1/uploads/meta/{uploadId}")
    public FileMeta getFileMeta(@PathVariable String uploadId)
    {
        return uploadService.getFileMeta(uploadId);
    }

    @RequestMapping(value="/rest/v1/uploads", method=RequestMethod.POST)
    public ResultResponse handleUpload(@RequestParam("name") String name, // Ugly as fuck...
                                       @RequestParam(value="attachment", defaultValue="false") boolean attachment,
                                       @RequestParam("file") MultipartFile file) throws IOException
    {
        // TODO: Get user ID from session
        FileMeta fileMeta = uploadService.uploadFile(new FileMeta("todo:userid", name, attachment, new Date()), file.getInputStream());
        return newOkResult(MessageConstant.FILE_UPLOADED, fileMeta);
    }

    @RequestMapping(value="/rest/v1/uploads/{fileId}", method=RequestMethod.GET)
    public void getUpload(@PathVariable String fileId, HttpServletResponse response) throws IOException
    {
        DownloadRequest downloadRequest = uploadService.downloadFile(fileId, response.getOutputStream());
        if (downloadRequest == null || downloadRequest.getFileMeta() == null)
        {
            response.sendError(404);
            return;
        }
        FileMeta fileMeta = downloadRequest.getFileMeta();
        if (fileMeta.isAttachment())
        {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileMeta.getName() + "\"");
        }
        else
        {
            response.setHeader("Content-Disposition", "filename=\"" + fileMeta.getName() + "\"");
        }
        downloadRequest.redirect();
    }
}
