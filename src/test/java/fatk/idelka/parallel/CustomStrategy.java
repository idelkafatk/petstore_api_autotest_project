package fatk.idelka.parallel;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class CustomStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {
    // Стратегия fixed не позволяет выбрать количество потоков для параллельного запуска тестов

    int threads = Integer.parseInt(System.getProperty("threads"));
    @Override
    public int getParallelism() {
        return threads;
    }

    @Override
    public int getMinimumRunnable() {
        // Должно быть 0, чтобы работало
        return 0;
    }

    @Override
    public int getMaxPoolSize() {
        return threads;
    }

    @Override
    public int getCorePoolSize() {
        return threads;
    }

    @Override
    public int getKeepAliveSeconds() {
        return 10;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(ConfigurationParameters configurationParameters) {
        return this;
    }
}