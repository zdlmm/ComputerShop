package org.denglin.armeria.entity;

import java.util.List;

public record SearchResult(List<Cpu> cpus, List<Gpu> gpus, List<Memory> memories) {
}
