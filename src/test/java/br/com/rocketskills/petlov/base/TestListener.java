package br.com.rocketskills.petlov.base;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = BaseTest.getDriver();
        if (driver == null) {
            return;
        }

        try {
            if (driver instanceof TakesScreenshot) {
                byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // attach to Allure report
                String name = context.getDisplayName();
                Allure.addAttachment(name + " - screenshot", new ByteArrayInputStream(bytes));

                // also save to target/screenshots for CI artifact
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
                String ts = LocalDateTime.now().format(fmt);
                String fileName = name.replaceAll("[^a-zA-Z0-9-_\.]", "_") + "-" + ts + ".png";
                Path dir = Paths.get("target", "screenshots");
                Files.createDirectories(dir);
                Path out = dir.resolve(fileName);
                Files.write(out, bytes);
            }
        } catch (IOException e) {
            // best effort - don't rethrow to avoid hiding original failure
            e.printStackTrace();
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        // no-op
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        // no-op
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        // no-op
    }
}
