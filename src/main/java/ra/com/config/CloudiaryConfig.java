package ra.com.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudiaryConfig {
    private static final String CLOUD_NAME = "dtig9dlxu";
    private static final String API_KEY = "296782444583931";
    private static final String API_SECRET = "i-JSi0kwCoWlpP4HAttUznWoEdY";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET));
    }

}
