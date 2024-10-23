package lt.ca.javau10;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lt.ca.javau10.entities.ExampleExercise;
import lt.ca.javau10.entities.Theory;
import lt.ca.javau10.entities.Topic;
import lt.ca.javau10.repositories.AssessmentRepository;
import lt.ca.javau10.repositories.ExampleExerciseRepository;
import lt.ca.javau10.repositories.TheoryRepository;
import lt.ca.javau10.repositories.TopicRepository;
import lt.ca.javau10.services.TopicContentService;

class TopicContentServiceTest {

    @Mock
    private ExampleExerciseRepository exRep;

    @Mock
    private TheoryRepository theoryRep;

    @Mock
    private TopicRepository topicRep;

    @Mock
    private AssessmentRepository aRep;

    @InjectMocks
    private TopicContentService topicContentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTheories() {
        // Arrange
        List<Theory> mockTheories = Arrays.asList(new Theory(), new Theory());
        when(theoryRep.findAll()).thenReturn(mockTheories);

        // Act
        List<Theory> result = topicContentService.getAllTheories();

        // Assert
        assertEquals(2, result.size());
        verify(theoryRep, times(1)).findAll();
    }

    @Test
    void testGetAllProblems() {
        // Arrange
        List<ExampleExercise> mockExercises = Arrays.asList(new ExampleExercise(), new ExampleExercise());
        when(exRep.findAll()).thenReturn(mockExercises);

        // Act
        List<ExampleExercise> result = topicContentService.getAllProblems();

        // Assert
        assertEquals(2, result.size());
        verify(exRep, times(1)).findAll();
    }

    @Test
    void testGetTheoriesByTopicName() {
        // Arrange
        String title = "Math";
        Theory theory1 = new Theory();
        theory1.setTopic(new Topic(title));
        Theory theory2 = new Theory();
        theory2.setTopic(new Topic("Science"));
        when(theoryRep.findAll()).thenReturn(Arrays.asList(theory1, theory2));

        // Act
        List<Theory> result = topicContentService.getTheoriesByTopicName(title);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Math", result.get(0).getTopic().getTitle());
    }

    @Test
    void testGetTheoriesByTopicID() {
        // Arrange
        Long topicId = 1L;
        Topic mockTopic = new Topic();
        mockTopic.setId(topicId);
        Theory theory = new Theory();
        mockTopic.setTheories(Arrays.asList(theory));
        when(topicRep.findById(topicId)).thenReturn(Optional.of(mockTopic));

        // Act
        List<Theory> result = topicContentService.getTheoriesByTopicID(topicId);

        // Assert
        assertEquals(1, result.size());
        verify(topicRep, times(1)).findById(topicId);
    }

    @Test
    void testGetTheoryById() {
        // Arrange
        Long theoryId = 1L;
        Theory mockTheory = new Theory();
        when(theoryRep.findById(theoryId)).thenReturn(Optional.of(mockTheory));

        // Act
        Theory result = topicContentService.getTheoryById(theoryId);

        // Assert
        assertNotNull(result);
        verify(theoryRep, times(1)).findById(theoryId);
    }

    @Test
    void testCreateNewTheory() {
        // Arrange
        Theory mockTheory = new Theory();
        when(theoryRep.save(mockTheory)).thenReturn(mockTheory);

        // Act
        Theory result = topicContentService.createNewTheory(mockTheory);

        // Assert
        assertNotNull(result);
        verify(theoryRep, times(1)).save(mockTheory);
    }

    @Test
    void testCreateAndAddNewTheoryToTopic() {
        // Arrange
        Long topicId = 1L;
        Topic mockTopic = new Topic();
        Theory newTheory = new Theory();
        when(topicRep.findById(topicId)).thenReturn(Optional.of(mockTopic));
        when(theoryRep.save(newTheory)).thenReturn(newTheory);

        // Act
        Theory result = topicContentService.createAndAddNewTheoryToTopic(topicId, newTheory);

        // Assert
        assertNotNull(result);
        assertEquals(mockTopic, newTheory.getTopic());
        verify(topicRep, times(1)).findById(topicId);
        verify(topicRep, times(1)).save(mockTopic);
        verify(theoryRep, times(1)).save(newTheory);
    }

    @Test
    void testDeleteTheory() {
        // Arrange
        Long theoryId = 1L;
        Theory mockTheory = new Theory();
        when(theoryRep.findById(theoryId)).thenReturn(Optional.of(mockTheory));

        // Act
        String result = topicContentService.deleteTheory(theoryId);

        // Assert
        assertEquals("succesfully removed", result);
        verify(theoryRep, times(1)).delete(mockTheory);
    }

    @Test
    void testUpdateTheory() {
        // Arrange
        Long theoryId = 1L;
        Theory oldTheory = new Theory();
        Theory updatedTheory = new Theory();
        updatedTheory.setTitle("New Title");
        updatedTheory.setContent("New Content");
        when(theoryRep.findById(theoryId)).thenReturn(Optional.of(oldTheory));
        when(theoryRep.save(oldTheory)).thenReturn(oldTheory);

        // Act
        Theory result = topicContentService.updateTheory(theoryId, updatedTheory);

        // Assert
        assertNotNull(result);
        assertEquals("New Title", oldTheory.getTitle());
        assertEquals("New Content", oldTheory.getContent());
        verify(theoryRep, times(1)).save(oldTheory);
    }
}
