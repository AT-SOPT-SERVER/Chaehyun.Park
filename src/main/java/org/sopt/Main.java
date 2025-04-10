package org.sopt;

import java.util.List;
import java.util.Scanner;
import org.sopt.controller.PostController;
import org.sopt.domain.Post;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PostController controller = new PostController();

        printWelcome();

        while (true) {
            printMenu();

            System.out.print("👉 선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("\n📝 [게시글 작성]");

                    long currentTime = System.currentTimeMillis();
                    if(!controller.checkPostTime(currentTime)) {
                        System.out.println("❌ 새로운 게시글은 마지막 게시글 작성 이후 3분 후에 작성할 수 있습니다.");
                        break;
                    }

                    String promptMsg = "📌 제목을 입력해주세요: ";
                    while (true) {
                        System.out.print(promptMsg);
                        String title = scanner.nextLine();

                        if (title.length() > 30 || title.isBlank()) {
                            System.out.println("❌ 제목은 1자 이상 30자 이하로 작성해주세요.");
                            promptMsg = "📌 제목을 다시 입력해주세요: ";
                            continue;
                        }

                        if (!controller.createPost(title)) {
                            System.out.println("❌ 이미 존재하는 제목입니다. 다른 제목을 사용해주세요.");
                            promptMsg = "📌 제목을 다시 입력해주세요: ";
                            continue;
                        } else {
                            System.out.println("✅ 게시글이 성공적으로 저장되었습니다!");
                            break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\n📚 [전체 게시글 조회]");
                    for (Post post : controller.getAllPosts()) {
                        System.out.printf("🆔 %d | 📌 제목: %s\n", post.getId(), post.getTitle());
                    }
                    break;

                case "3":
                    System.out.println("\n🔍 [게시글 상세 조회]");
                    System.out.print("📌 조회할 게시글 ID를 입력해주세요: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Post found = controller.getPostById(id);
                    if (found != null) {
                        System.out.println("📄 게시글 상세 내용:");
                        System.out.println("-------------------------------------");
                        System.out.printf("🆔 ID: %d\n", found.getId());
                        System.out.printf("📌 제목: %s\n", found.getTitle());
                        System.out.println("-------------------------------------");
                    } else {
                        System.out.println("❌ 해당 ID의 게시글이 존재하지 않습니다.");
                    }
                    break;

                case "4":
                    System.out.println("\n✏️ [게시글 수정]");
                    System.out.print("📌 수정할 게시글 ID를 입력해주세요: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("📝 새 제목을 입력해주세요: ");
                    String newTitle = scanner.nextLine();
                    boolean updated = controller.updatePostTitle(updateId, newTitle);
                    if (updated) {
                        System.out.println("✅ 게시글이 성공적으로 수정되었습니다.");
                    } else {
                        System.out.println("❌ 해당 ID의 게시글이 존재하지 않습니다.");
                    }
                    break;

                case "5":
                    System.out.println("\n🗑️ [게시글 삭제]");
                    System.out.print("📌 삭제할 게시글 ID를 입력해주세요: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    boolean deleted = controller.deletePostById(deleteId);
                    if (deleted) {
                        System.out.println("🗑️ 게시글이 성공적으로 삭제되었습니다.");
                    } else {
                        System.out.println("❌ 삭제할 게시글이 존재하지 않습니다.");
                    }
                    break;

                case "6":
                    System.out.println("\n🔎 [게시글 검색]");
                    System.out.print("검색할 키워드를 입력해주세요: ");
                    String keyword = scanner.nextLine();
                    List<Post> results = controller.searchPostsByKeyword(keyword);
                    if (results.isEmpty()) {
                        System.out.println("🔍 검색 결과가 없습니다.");
                    } else {
                        System.out.println("📋 검색 결과:");
                        for (Post post : results) {
                            System.out.printf("🆔 %d | 📌 제목: %s\n", post.getId(), post.getTitle());
                        }
                    }
                    break;

                case "0":
                    System.out.println("\n👋 프로그램을 종료합니다. 감사합니다!");
                    return;

                default:
                    System.out.println("⚠️ 잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void printWelcome() {
        System.out.println("=====================================");
        System.out.println("📌  자바 게시판 프로그램에 오신 것을 환영합니다!");
        System.out.println("=====================================\n");
    }

    private static void printMenu() {
        System.out.println("\n================ 메뉴 ================");
        System.out.println("1️⃣  게시글 작성");
        System.out.println("2️⃣  전체 게시글 조회");
        System.out.println("3️⃣  게시글 상세 조회");
        System.out.println("4️⃣  게시글 수정");
        System.out.println("5️⃣  게시글 삭제");
        System.out.println("6️⃣  게시글 검색");
        System.out.println("0️⃣  프로그램 종료");
        System.out.println("=====================================");
    }
}
