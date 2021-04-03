package pl.robert.repos.app.shared;

public final class UrlHelper {

    public final static String GITHUB_API_URL = "https://api.github.com";

    public static String buildUrl(String... parts) {
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part);
        }
        return sb.toString();
    }
}
