package cc.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface LogMapper {

	int insert(Map map);
}
