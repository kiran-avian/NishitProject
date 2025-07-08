package com.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.libraryFiles.BaseClass;

public class BrokenLink extends BaseClass {
	@Test
	public void checkBrokenLinks() throws IOException, InterruptedException {
		initialiseBrowser();
		driver.get("https://caronphone.com/");
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10))
				.followRedirects(HttpClient.Redirect.NORMAL).build();

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links found: " + links.size());

		List<String> brokenLinks = new ArrayList<>();

		for (WebElement link : links) {
			String href = link.getDomAttribute("href");
			System.out.println(href);
			// Filter out bad or unsupported links
			if (href == null || href.isEmpty() || href.startsWith("javascript:") || href.startsWith("mailto:")
			        || href.startsWith("tel:")) {
			    continue;
			}

			// Convert relative URLs to absolute
			if (!href.startsWith("http://") && !href.startsWith("https://")) {
			    href = driver.getCurrentUrl().replaceAll("/+$", "") + "/" + href.replaceAll("^/+", "");
			}


			try {
				HttpRequest request = HttpRequest.newBuilder().uri(new URI(href))
						.method("HEAD", HttpRequest.BodyPublishers.noBody()).timeout(Duration.ofSeconds(10)).build();

				HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

				int status = response.statusCode();
				if (status >= 400) {
					System.out.println("❌ Broken link: " + href + " -> Status: " + status);
					brokenLinks.add(href);
				} else {
					System.out.println("✅ Valid link: " + href + " -> Status: " + status);
				}

			} catch (URISyntaxException e) {
				System.out.println("❌ Invalid URL: " + href + " (" + e.getMessage() + ")");
			} catch (IOException | InterruptedException e) {
				System.out.println("❌ Error for URL: " + href + " (" + e.getMessage() + ")");
			}
		}

		// Summary
		System.out.println("\n=== Summary ===");
		System.out.println("Total broken links: " + brokenLinks.size());
		brokenLinks.forEach(System.out::println);
	}
}