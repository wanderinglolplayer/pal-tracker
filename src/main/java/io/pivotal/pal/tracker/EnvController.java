package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    private String port;
    private String memory_limit;
    private String cf_instance_index;
    private String cf_instance_addr;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}")String memory_limit,
                         @Value("${cf.instance.index:NOT SET}") String cf_instance_index,
                         @Value("${cf.instance.addr:NOT SET}") String cf_instance_addr) {
        this.port = port;
        this.memory_limit = memory_limit;
        this.cf_instance_index = cf_instance_index;
        this.cf_instance_addr = cf_instance_addr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return new HashMap(){
            {
                put("PORT", port);
                put("MEMORY_LIMIT", memory_limit);
                put("CF_INSTANCE_INDEX", cf_instance_index);
                put("CF_INSTANCE_ADDR", cf_instance_addr);
            }
        };
    }
}
