package hanghae99.ditto.newsfeed.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsfeedRequest {
    private Long feedMemberId;
    private Long senderId;
    private Long receiverId;
    private String type;
}