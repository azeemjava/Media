package in.triton.multipart.file.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import in.triton.multipart.file.entity.ImageData;



@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<ImageData,Long>{
    Optional<ImageData> findByName(String fileName);

}
