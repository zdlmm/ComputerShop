package org.denglin.armeria;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.annotation.Get;
import org.denglin.armeria.entity.Cpu;
import org.denglin.armeria.entity.Gpu;
import org.denglin.armeria.entity.Memory;
import org.denglin.armeria.entity.SearchResult;

import java.util.ArrayList;
import java.util.List;

final class ComponentService {

    private final List<Cpu> cpus = new ArrayList<>();
    private final List<Gpu> gpus = new ArrayList<>();
    private final List<Memory> memories = new ArrayList<>();

    public ComponentService() {
        dataInit();
    }

    @Get("/all")
    public HttpResponse findAllAvailableItems() {
        SearchResult searchResult = new SearchResult(cpus, gpus, memories);
        return HttpResponse.ofJson(searchResult);
    }

    public void dataInit() {
        Cpu cpu1 = new Cpu("15162b96-d8aa-4227-b8ed-e08912d46ac3", "Intel Core i5-13400", "Intel", 250, 25);
        Cpu cpu2 = new Cpu("8614b9ef-3429-44b8-b538-4d4ed1e61b77", "Intel Core i9-14700", "Intel", 455, 10);
        cpus.add(cpu1);
        cpus.add(cpu2);
        Gpu gpu1 = new Gpu("8feea0cf-cab8-4e94-a802-cdd4a35cbc9e", "GeForce RTX 4060", "NVIDIA", 400, 15);
        Gpu gpu2 = new Gpu("36fa6546-423d-4a17-b0d4-ee3d91851308", "Radeon 7600", "AMD", 270, 5);
        gpus.add(gpu1);
        gpus.add(gpu2);
        Memory memory = new Memory("cb427efc-4490-4aea-9146-784363532878", "Kingston FURY Beast DDR5", "Kingston", 125, 10);
        memories.add(memory);
    }
}
