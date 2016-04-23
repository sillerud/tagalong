package no.westerdals.tagalong.uploads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import no.westerdals.tagalong.model.FileMeta;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@AllArgsConstructor
public class DownloadRequest
{
    private InputStream in;
    private OutputStream out;
    @Getter
    private FileMeta fileMeta;

    public void redirect() throws IOException
    {
        byte[] buffer = new byte[4096];
        int len;
        while ((len = in.read(buffer)) >= 0)
        {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
}
